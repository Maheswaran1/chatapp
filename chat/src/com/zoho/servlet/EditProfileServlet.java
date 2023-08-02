package com.zoho.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.zoho.model.User;
import com.zoho.utility.InMemoryCache;
import com.zoho.utility.ThreadLocalUtil;
import proxy.ProxyFactory;

public class EditProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = null;

		user = ThreadLocalUtil.getCurrentUser();
		if (user == null) {
			response.sendRedirect("login.jsp");
		}

		LinkedHashMap<String, Object> map = new LinkedHashMap<>();

		if (request.getParameter("fname") != "" && request.getParameter("fname") != null && !request.getParameter("fname").equals(user.getFirstName())) {
			map.put("FirstName", request.getParameter("fname"));
		}
		if (request.getParameter("lname") != "" && request.getParameter("lname") != null && !request.getParameter("lname").equals(user.getLastName())) {
			map.put("LastName", request.getParameter("lname"));
		}
		if (request.getParameter("Gender") != "" && request.getParameter("Gender") != null && !request.getParameter("Gender").equals(user.getGender())) {
			map.put("Gender", request.getParameter("gender"));
		}

		String DOB = request.getParameter("dob");
		if (DOB != null && user.getDateOfBirth() != null && !DOB.equals(user.getDateOfBirth().toString())) {
			map.put("DateOfBirth", request.getParameter("dob"));
		}

		if (!map.isEmpty()) {
			ProxyFactory.getUserProxy().updateProfile(user.getUserId(),map);
			InMemoryCache userCache = InMemoryCache.getInstance();
			userCache.removeUser(user.getUserId());
		}
		response.sendRedirect("LoginSuccess.jsp");
	}

}