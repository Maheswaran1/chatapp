package com.zoho.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zoho.model.SessionManager;
import com.zoho.model.User;
import com.zoho.utility.InMemoryCache;
import com.zoho.utility.ThreadLocalUtil;
import com.zoho.utility.Utility;
import apiInterFace.SessionManagerInterface;
import proxy.ProxyFactory;

public class LoginFilter implements Filter {

	public void init(FilterConfig filter) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String sessionId = null;
		long validity = 0;
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("sessionId")) {
					sessionId = cookie.getValue();
				}
			}
		}
		if (sessionId == null) {
			res.sendRedirect("login.jsp");
		} else {
			// checking session in cache
			InMemoryCache sessionCache = InMemoryCache.getInstance();
			SessionManager session = sessionCache.get(sessionId);
			if (session == null) {
				// checking session Database
				session =  ((SessionManagerInterface) ProxyFactory.getSessionProxy()).getSessionObject(sessionId);
				if (session == null) {
					res.sendRedirect("login.jsp"); // session cache is not exist return to login
					return;
				}
				sessionCache.put(sessionId, session);
				// session exist add in cache
			}
			validity = session.getValidityTime();
			Utility utility = new Utility();
			if (validity > 0 && validity > utility.getCurrentTime()) {
				InMemoryCache userCache = InMemoryCache.getInstance();
				User user = userCache.getUser(session.getUserId()); // get User from Cache
				if (user == null) {
					try {
						user =  ProxyFactory.getUserProxy().getUser(session.getUserId());//getApi proxy Object
						if (user.getSpam() == 3) {
							res.sendRedirect("spam.jsp"); // checking spam
							return;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}  
				ThreadLocalUtil.setCurrentUser(user);
				chain.doFilter(req, res);
			} else {
				res.sendRedirect("login.jsp");
			}
		}
	}

	public void destory() {

	}
}
