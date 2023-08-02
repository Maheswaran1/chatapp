package com.zoho.RedisDao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import com.zoho.database.ExcuteQuery;
import com.zoho.database.RedisCache;
import com.zoho.model.ObjectFactory;

public class ClearCacheClass {

	RedisCache cache = RedisCache.getInstance();
	
	
	public void ClearCache(ObjectFactory object) {
		try {
			Class<?> className = Class.forName("com.zoho.model." + object.getTableName()+"RedisDao");
			Object refletionObject = className.getDeclaredConstructor().newInstance();
			Method method = className.getMethod("clearIn"+object.getTableName(), ObjectFactory.class);
			method.invoke(refletionObject, object);
		} catch (ClassNotFoundException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
