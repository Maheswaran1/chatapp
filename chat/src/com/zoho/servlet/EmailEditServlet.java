package com.zoho.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zoho.model.User;
import com.zoho.utility.ThreadLocalUtil;
import apiInterFace.EmailInterface;
import proxy.ProxyFactory;

public class EmailEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("button");
		String emailId = request.getParameter("emailId");
		String newEmail = request.getParameter("newEmail");

		User user = ThreadLocalUtil.getCurrentUser();
		switch (action) {

		case "deleteEmail": {
			 ProxyFactory.getEmailProxy().delete(emailId);
			break;
		}
		case "setAsPrimary": {
			((EmailInterface) ProxyFactory.getEmailProxy()).setPrimaryEmail(emailId, user.getUserId());
			break;
		}
		case "addEmail": {
			EmailInterface emailApi = (EmailInterface) ProxyFactory.getEmailProxy();
			long exist = emailApi.checkExistingEmail(newEmail, user.getUserId());
			if (exist == 0) {
				Boolean priamary = false;
				emailApi.addEmail(newEmail, user.getUserId(), priamary, true);
			}
			break;
		}

		}
		response.sendRedirect("EmailEdit.jsp");
	}

}
