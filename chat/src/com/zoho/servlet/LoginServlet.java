package com.zoho.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.zoho.api.EmailDao;
import com.zoho.api.PasswordDao;
import com.zoho.api.SessionManagerDao;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("serial")

public class LoginServlet extends HttpServlet {

	public static HashMap<String, Object> objectMap = new HashMap<>();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email = request.getParameter("EmailId");
		String password = request.getParameter("Password");
		
		

		EmailDao emailapi = new EmailDao();
		String userId = checkEmail(emailapi,email);
		
		if(userId == null) {
			response.sendRedirect("login.jsp");
		}else {
		PasswordDao passwordApi = new PasswordDao();	
		HashMap<String ,Object> passwordMap = new HashMap<String, Object>();
		passwordMap.put("UserId", userId);
		boolean authentication = authenticateUser(passwordApi,passwordMap,password);
		
		
		
		SessionManagerDao session = new SessionManagerDao();
		HashMap<String, Object> sessionMap =new HashMap<String, Object>();
		
		String sessionId = generatedSessionId() ;
		sessionMap.put("Sessionid", sessionId);
		sessionMap.put("UserId", userId);
		session.InsertSession(sessionMap);
				
		if(authentication) { 
			Cookie user = new Cookie("sessionId",sessionId);
			user.setMaxAge(3600);
			response.addCookie(user);
			
			response.sendRedirect("LoginSuccess.jsp");
		}else {
			response.sendRedirect("login.jsp");
		}
	}
		
	}
	
	
	
	private String generatedSessionId() {
		return String.valueOf(System.currentTimeMillis()).substring(8,13)+UUID.randomUUID().toString().substring(1,10);
	}



	public String checkEmail(EmailDao emailApi,String emailId) {
		try {
			String userId = emailApi.checkEmail(emailId);
			return userId;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Please Enter valid EmailId");
		}
		return null;
	}
	
	public boolean  authenticateUser(PasswordDao passwordApi,HashMap<String, Object> condition, String password) {
		try {
			boolean check =passwordApi.authanticateUser(password, condition);
			return check;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
}
