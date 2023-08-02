package com.zoho.database;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;

import com.zoho.RedisDao.ClearCacheClass;
import com.zoho.api.AuditDao;
import com.zoho.model.Audit;
import com.zoho.model.ObjectFactory;

public class ExcuteQuery {

	public ObjectFactory getInstance(String tableName, HashMap<String, Object> map) throws Exception {
		try {
			Class<?> className = Class.forName("com.zoho.model." + tableName);
			Constructor<?> construtor = className.getConstructor(HashMap.class);
			return (ObjectFactory) construtor.newInstance(map);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {

			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("static-access")
	public ObjectFactory select(String tableName, String[] column, HashMap<String, Object> condition) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String query = SelectQueryGeneretor(tableName, column, condition); // QueryGeneretor method in class
		DbcpImplementation connectionInstance = DbcpImplementation.getInstance();
		Connection connect = connectionInstance.getConnection();

		Statement pst = connect.createStatement();

		ResultSet result = pst.executeQuery(query);
		ResultSetMetaData rsmd = result.getMetaData();
		int columnCount = rsmd.getColumnCount();

		while (result.next()) {
			for (int i = 0; i < columnCount; i++) {

				map.put(rsmd.getColumnLabel(i + 1), result.getObject(i + 1));
			}
		}

		ObjectFactory objfactory = getInstance(tableName, map);
		connect.close();
		return objfactory;
	}

	public ArrayList<ObjectFactory> selectMultiple(String tableName, String[] column, HashMap<String, Object> condition)
			throws Exception {
		ArrayList<ObjectFactory> objectList = new ArrayList<ObjectFactory>();
		String query = SelectQueryGeneretor(tableName, column, condition); // QueryGeneretor method in class
		DbcpImplementation connectionInstance = DbcpImplementation.getInstance();
		Connection connect = connectionInstance.getConnection();

		Statement pst = connect.createStatement();

		ResultSet result = pst.executeQuery(query);
		ResultSetMetaData rsmd = result.getMetaData();
		int columnCount = rsmd.getColumnCount();

		while (result.next()) {
			HashMap<String, Object> map = new HashMap<String, Object>();

			for (int i = 0; i < columnCount; i++) {

				map.put(rsmd.getColumnLabel(i + 1), result.getObject(i + 1));
			}
			ObjectFactory objFactory = getInstance(tableName, map);
			objectList.add(objFactory);
		}
		connect.close();
		return objectList;
	}

	public int insert(ObjectFactory object) throws Exception {
		ArrayList<ObjectFactory> objectList = new ArrayList<>();
		String insert = insertQueryGeneretor(object);
		int status = getResult(insert);
		objectList.add(object);
		AuditDao audit = new AuditDao();
		audit.insert(objectList);
		return status;
	}

	public int insertMultiple(ArrayList<ObjectFactory> objectList) throws NamingException, SQLException {
		DbcpImplementation connectionInstance = DbcpImplementation.getInstance();
		Connection connection = connectionInstance.getConnection();
		int status = 0;
		try {

			connection.setAutoCommit(false);
			for (int i = 0; i < objectList.size(); i++) {
				String query = insertQueryGeneretor(objectList.get(i));
				Statement statement = connection.createStatement();
				status = statement.executeUpdate(query);
			}
			AuditDao audit = new AuditDao();
			audit.insert(objectList);

			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}
		connection.close();
		return status;
	}

	public int update(String tableName, HashMap<String, Object> updateValues, HashMap<String, Object> condition)
			throws Exception {
		if (condition == null) {
			return 0;
		}
		Audit auditObject = null;
		HashMap<String,Object> oldValue = new HashMap<String, Object>();
		oldValue.putAll(condition);
		oldValue.putAll(updateValues);
		ObjectFactory objfactory = getInstance(tableName, oldValue);
		AuditDao audit = new AuditDao();
		auditObject = audit.getAuidtObject(objfactory, condition);

		StringBuilder update = new StringBuilder("UPDATE ");
		update.append(tableName).append(" SET ");
		int i = 0;

		for (Map.Entry<String, Object> value : updateValues.entrySet()) {
			if (!(value.getValue() instanceof Boolean)) {
				update.append(value.getKey() + " = '" + value.getValue() + "'");
			} else {
				update.append(value.getKey() + " = " + value.getValue());
			}
			if (i != updateValues.size() - 1) {
				update.append(",");
			}
			i++;
		}
		update.append(" WHERE ");

		for (Map.Entry<String, Object> condition1 : condition.entrySet()) {
			if (!(condition1.getValue() instanceof Boolean)) {
				update.append(condition1.getKey() + " = '" + condition1.getValue() + "'");
			} else {
				update.append(condition1.getKey() + " = " + condition1.getValue());
			}
		}
		update.append(";");
		System.out.println(update);
		int status = getResult(update.toString());
		insertAudit(auditObject);
		ClearCacheClass clearCache = new ClearCacheClass();
		//clearCache.clearAllCache(tableName,condition);
		return status;

	}

	public int delete(String tableName, HashMap<String, Object> condition) throws SQLException {
		if (condition == null) {
			return 0;
		}
		AuditDao audit = new AuditDao();
		Audit object = (Audit) audit.deleteinsert(tableName, condition);
		StringBuilder delete = new StringBuilder("DELETE ");
		delete.append("FROM ").append(tableName).append(" WHERE ");
		for (Map.Entry<String, Object> condition1 : condition.entrySet()) {
			delete.append(condition1.getKey() + " = '" + condition1.getValue() + "'");
		}
		System.out.println(delete.toString());
		int status = getResult(delete.toString());
		if (status > 0) {
			insertAudit(object);
		}
		
		return status;

	}

	private String insertQueryGeneretor(ObjectFactory object) {

		String tableName = object.getTableName();
		HashMap<String, Object> map = object.getMap();

		StringBuilder insert = new StringBuilder();
		StringBuilder values = new StringBuilder();

		insert.append("INSERT INTO " + tableName + " (");
		values.append("VALUES (");

		int i = 0;

		for (Map.Entry<String, Object> value : map.entrySet()) {
			insert.append(value.getKey());
			if (!(value.getValue() instanceof Boolean)) {
				values.append("'" + value.getValue() + "'");
			} else {
				values.append(value.getValue());
			}

			if (i != map.size() - 1) {
				insert.append(",");
				values.append(",");
			}
			i++;
		}
		values.append(")");
		insert.append(")" + values + ";");
		System.out.println(insert.toString());
		return insert.toString();
	}

	public int getResult(String query) throws SQLException {
		int status = 0;
		Connection connection = null;
		try {
			@SuppressWarnings("static-access")
			DbcpImplementation connectionInstance = DbcpImplementation.getInstance();
			connection = connectionInstance.getConnection();
			Statement statement = connection.createStatement();
			status = statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return status;
	}

	private String SelectQueryGeneretor(String tableName, String[] column, HashMap<String, Object> condition) {
		StringBuilder select = new StringBuilder();
		select.append("SELECT ");
		if (column != null) {
			for (int i = 0; i < column.length; i++) {
				select.append(column[i]);

				if (i != column.length - 1) {
					select.append(",");
				}
			}
		}
		select.append(" FROM " + tableName);
		if (condition != null) {
			select.append(" WHERE ");

			if (condition.size() > 1) {
				for (Map.Entry<String, Object> m : condition.entrySet()) {
					@SuppressWarnings("unchecked")
					HashMap<String, Object> addCondition = (HashMap<String, Object>) m.getValue();
					for (Map.Entry<String, Object> map : addCondition.entrySet()) {
						select.append(map.getKey() + " = '" + map.getValue() + "'");
					}
					select.append(m.getKey());
				}
			} else {
				for (Map.Entry<String, Object> m : condition.entrySet()) {
					select.append(m.getKey() + " = '" + m.getValue() + "'");
				}
			}

		}
		System.out.println(select);
		return select.toString();

	}

	public void insertAudit(Audit audit) throws SQLException {
		String insert = insertQueryGeneretor(audit);
		getResult(insert);
	}

}
