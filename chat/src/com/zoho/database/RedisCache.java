package com.zoho.database;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import com.zoho.model.ObjectFactory;

import redis.clients.jedis.Jedis;

public class RedisCache {

	private static RedisCache redisCache;

	public static RedisCache getInstance() {
		if (redisCache == null) {
			synchronized (RedisCache.class) {
				if (redisCache == null) {
					redisCache = new RedisCache();
				}
			}
		}
		return redisCache;
	}
//	public ObjectFactory getInstance(String tableName, HashMap<String, Object> map) throws Exception {
//		try {
//			Class<?> className = Class.forName("com.zoho.model." + tableName);
//			Constructor<?> construtor = className.getConstructor(HashMap.class);
//			return (ObjectFactory) construtor.newInstance(map);
//		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	
//	public void set(String key,String field ,Object cacheObj) throws IOException {
//		RedisConnectionPool redisConnection = RedisConnectionPool.getInstance();
//		Jedis jedis = redisConnection.getConnection();
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//		objectOutputStream.writeObject(cacheObj);
//		String objectString = new String(outputStream.toByteArray(), StandardCharsets.ISO_8859_1);
//		
//		jedis.hset(key, field,objectString);
//		objectOutputStream.close();
//		jedis.close();
//		
//	}
//	
//	public ObjectFactory getObject(String key,String filed,String tableName) throws IOException, ClassNotFoundException {
//		if(filed == null) {
//			return null;
//		}
//		RedisConnectionPool redisConnection = RedisConnectionPool.getInstance();
//		Jedis jedis = redisConnection.getConnection();
//		String objectString = jedis.hget(key,filed);
//		
//		ObjectFactory object = null;
//		byte[] objectBytes = objectString.getBytes(StandardCharsets.ISO_8859_1);
//		ByteArrayInputStream inputStream = new ByteArrayInputStream(objectBytes);
//		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
//		object = (ObjectFactory) objectInputStream.readObject();
//		objectInputStream.close();
//		jedis.close();
//		return object;
//		
//	}
//
//	
//	public long removeFromCache(String key,String field) {
//		RedisConnectionPool redisConnection = RedisConnectionPool.getInstance();
//		Jedis jedis = redisConnection.getConnection();
//		long status = jedis.hdel(key,field);
//		
//		jedis.close();
//		return status;
//	}
//	public String getObjectString(String string, String userId) {
//		if(userId == null) {
//			return null;
//		}
//		RedisConnectionPool redisConnection = RedisConnectionPool.getInstance();
//		Jedis jedis = redisConnection.getConnection();
//		String objectString = jedis.hget(string,userId);
//		jedis.close();
//		return objectString;
//	}
//	
//	public void lpush(String key ,String objectString) {
//		RedisConnectionPool redisConnection = RedisConnectionPool.getInstance();
//		Jedis jedis = redisConnection.getConnection();
//		jedis.lpush(key, objectString);
//		
//	}
//	

//
//	
//	public long removeFromCache(String key) {
//		RedisConnectionPool redisConnection = RedisConnectionPool.getInstance();
//		Jedis jedis = redisConnection.getConnection();
//		long status = jedis.del(key);
//		
//		jedis.close();
//		return status;
//	}
//	
//	
////	public void set(String userId, String idName, CacheObject cacheObj) throws IOException {
////		RedisConnectionPool redisConnection = RedisConnectionPool.getInstance();
////		Jedis jedis = redisConnection.getConnection();
////		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
////		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
////		objectOutputStream.writeObject(cacheObj);
////		objectOutputStream.close();
////		String objectString = new String(outputStream.toByteArray(), StandardCharsets.ISO_8859_1);
////		jedis.hset(userId, idName,objectString);
////		jedis.close();
////		
////	}
	public Object getFromJedis(String key) throws Exception {

		RedisConnectionPool redisConnection = RedisConnectionPool.getInstance();
		Jedis jedis = redisConnection.getConnection();
		String userString =  jedis.get(key);
		jedis.close();
		if (userString == null) {
			return null;
		}
		Object object = stringToObject(userString);
		return object;
	}
	public Object stringToObject(String value) throws IOException, ClassNotFoundException {
		byte[] objectBytes = value.getBytes(StandardCharsets.ISO_8859_1);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(objectBytes);
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		Object object = objectInputStream.readObject();
		objectInputStream.close();
		return object;
	}
	
	public List<String> getListObject(String key) {
		if(key == null) {
			return null;
		}
		RedisConnectionPool redisConnection = RedisConnectionPool.getInstance();
		Jedis jedis = redisConnection.getConnection();
		List<String> objectString = jedis.lrange(key, 0, -1);
		jedis.close();
		return objectString;
		
	}

	public void setObjectInRedis(String key, Object object) throws IOException {
		RedisConnectionPool redisConnection = RedisConnectionPool.getInstance();
		Jedis jedis = redisConnection.getConnection();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		objectOutputStream.writeObject(object);
		objectOutputStream.close();
		String objectString = new String(outputStream.toByteArray(), StandardCharsets.ISO_8859_1);
		jedis.set(key, objectString);
		jedis.close();
	}

	public void setDataInRedis(String key, String objectString) {
		RedisConnectionPool redisConnection = RedisConnectionPool.getInstance();
		Jedis jedis = redisConnection.getConnection();
		jedis.lpush(key, objectString);
	}
}
