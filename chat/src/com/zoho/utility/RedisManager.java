package com.zoho.utility;

public class RedisManager {
	private static Process redisProcess;
	public static void startRedis() {
        try {
            String command = "redis-server";
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            redisProcess = processBuilder.start();
            System.out.println("Redis started successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopRedis() {
        if (redisProcess != null) {
            redisProcess.destroy();
            System.out.println("Redis stopped successfully.");
        }
    }
}
