package com.zoho.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zoho.api.EmailDao;
import com.zoho.api.PasswordDao;
import com.zoho.api.PhoneDao;
import com.zoho.api.UserDao;
import com.zoho.model.ObjectFactory;




@SuppressWarnings("serial")
public class SignUpServlet extends HttpServlet {
			

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList<ObjectFactory> objectList = new ArrayList<>();
	String firstName = request.getParameter("FirstName");
	String lastName = request.getParameter("LastName");
	String gender = request.getParameter("Gender");
	String dateOfBirth = request.getParameter("DateOfBirth");
	String emailId = request.getParameter("EmailId");
	String password = request.getParameter("Password");
	String phoneNumber = request.getParameter("PhoneNumber");
	//String primaryEmail = request.getParameter("primaryEmail");
	
	HashMap<String,Object> userMap = new HashMap<>();
	userMap.put("FirstName", firstName);
	userMap.put("LastName", lastName);
	userMap.put("Gender", gender);
	userMap.put("DateOfBirth", dateOfBirth);
	UserDao user = new UserDao();
	objectList.add(user.registerUser(userMap));
	
	
	HashMap<String, Object> emailMap = new HashMap<>();
	emailMap.put("UserId",userMap.get("UserId"));
	emailMap.put("EmailId", emailId);
	EmailDao email = new EmailDao();
	email.getObject(emailMap);
	objectList.add(email.getObject(emailMap));
	
	PasswordDao passwordApi = new PasswordDao();
	HashMap<String, Object> passwordMap = new HashMap<String,Object>();
	passwordMap.put("UserId",userMap.get("UserId"));
	try {
		objectList.add(passwordApi.getObject(passwordMap,password));
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	HashMap<String,Object> phoneMap = new HashMap<>();
	phoneMap.put("UserId", userMap.get("UserId"));
	phoneMap.put("PhoneNumber",phoneNumber);
	//phoneMap.put("primaryEmail",primaryEmail);
	PhoneDao phone = new PhoneDao();
	objectList.add(phone.getObject(phoneMap));
	try {
			UserDao object = new UserDao();
		int status = object.insertUser(objectList);
		if(status>0) {
			response.sendRedirect("login.jsp");
		}
	} catch (Exception e) {
		
		e.printStackTrace();
	}

}
}


