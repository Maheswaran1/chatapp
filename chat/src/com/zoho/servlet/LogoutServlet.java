package com.zoho.servlet;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zoho.api.SessionManagerDao;


@SuppressWarnings("serial")
public class LogoutServlet  extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sessionId = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie :cookies) {
				if(cookie.getName().equals("sessionId")) {
					sessionId = cookie.getValue();
					deleteSession(sessionId);
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					break;
				}
			}
		}
		response.sendRedirect("Home.html");
	}
	
	public void deleteSession(String sessionId) {
		SessionManagerDao session = new SessionManagerDao();
		session.removeSession(sessionId);
		
	}
}
