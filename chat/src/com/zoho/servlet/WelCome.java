package com.zoho.servlet;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.zoho.api.IdGeneratorApi;
import com.zoho.utility.*;


public class WelCome implements ServletContextListener{

   
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Server has started");
		IdGeneratorApi idGeneretor = IdGeneratorApi.getInstance();
		RedisManager.startRedis();
		
		try {
			
			idGeneretor.selectValues();
		} catch (Exception e) {
			System.out.println("not done");
			e.printStackTrace();
		}
	}

   public void contextDestroyed(ServletContextEvent sce) {
   
	   System.out.println("server has to be shutdown");
	   IdGeneratorApi idGeneretor =IdGeneratorApi.getInstance();
	   try {
		   RedisManager.stopRedis();
		   idGeneretor.updateValues();
		   System.out.println("Ended");
		  
		   
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
   }
}
  