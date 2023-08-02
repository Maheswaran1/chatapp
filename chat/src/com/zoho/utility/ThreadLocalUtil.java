package com.zoho.utility;

import com.zoho.model.User;

public class ThreadLocalUtil {
	 private static  ThreadLocal<User> currentUser = new ThreadLocal<User>();
	 
	 
	 public static void setCurrentUser(User user){
		 currentUser.set(user);
	 }
	 
	 public static User getCurrentUser() {
		return currentUser.get();
	 }
	 
	public static void clearCurrentUser() {
		currentUser.remove();
	}
}
 