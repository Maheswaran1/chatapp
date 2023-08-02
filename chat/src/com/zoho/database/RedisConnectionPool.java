package com.zoho.database;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnectionPool {
		
	private static RedisConnectionPool connectionPool;
	
	public static RedisConnectionPool getInstance() {
		if(connectionPool == null) {
			synchronized(RedisConnectionPool.class) {
				if(connectionPool == null) {
					connectionPool = new RedisConnectionPool();
				}
			}
		}
		return connectionPool;
	}
	
	public Jedis getConnection() {

		JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10); 
        poolConfig.setMaxIdle(5); 
        JedisPool jedisPool =null;
      try {
    	   jedisPool = new JedisPool(poolConfig, "localhost", 6379);
      }catch(Exception e) {
    	  e.printStackTrace();
      }
        Jedis jedis = jedisPool.getResource();
        return jedis;
	}
	
	public void closeConnection(Jedis jedis) {
		jedis.close();
	}
}



