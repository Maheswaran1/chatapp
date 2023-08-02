package com.zoho.database;

		

	import java.sql.Connection;
	import java.sql.SQLException;

	import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

	public class DbcpImplementation {

		private static DbcpImplementation dbcpi;
		private static BasicDataSource basicData;

		private DbcpImplementation() {
			if (dbcpi != null) {
				throw new RuntimeException("Please use getInstance method");
			}
		}

		public static DbcpImplementation getInstance() throws SQLException {
			if (dbcpi == null) {
				synchronized (DbcpImplementation.class) {
					if (dbcpi == null) {
						dbcpi = new DbcpImplementation();
					}
				}
			}
			return dbcpi;
		}

		public Connection getConnection() {

			if (basicData == null) {
				synchronized (this) {
					if (basicData == null) {
						basicData = new BasicDataSource();
						try {
							basicData.setDriverClassName("com.mysql.cj.jdbc.Driver");
							basicData.setUrl("jdbc:mysql://localhost:3306/chatapp");
							basicData.setUsername("root");
							basicData.setPassword("");
							basicData.setMinIdle(5);
							basicData.setMaxIdle(10);
							basicData.setMaxOpenPreparedStatements(100);
							} catch (Exception e) {
								e.printStackTrace();
						}
					}
				}
			}try {
				return basicData.getConnection();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
			}
			

		public void closeConnection(Connection con) throws SQLException {
			con.close();
		}
	}

