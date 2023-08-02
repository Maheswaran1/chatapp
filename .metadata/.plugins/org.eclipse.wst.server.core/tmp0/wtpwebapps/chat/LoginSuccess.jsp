<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Success Page</title>
</head>
<body>
<form action="LoginSuccess">

<%@page import = "com.zoho.utility.*" %>
<%@page import = "com.zoho.model.*" %>
<%@page import = "java.util.*" %>
<%
	String sessionId = null;

	Cookie[] cookies = request.getCookies();
	if(cookies != null) {
		for(Cookie cookie : cookies){
		if(cookie.getName().equals("sessionId")) {
			sessionId = cookie.getValue();
		}
		
		}
		
	}
	if(sessionId == null) {
		response.sendRedirect("login.jsp");
	}
	User user = ThreadLocalUtil.getCurrentUser();
 %>
	
 <h1>Hi <%= user.getFirstName()%>, Login successful.</h1>
 
 </form>
	
<form  method="post">
		<input formaction="LogoutServlet" type="submit" value="Logout">
		<input formaction="EditProfile.jsp" type="submit" value="EditProfile"><br>
		<input formaction="EmailEdit.jsp" type="submit" value="Edit Email">
		
	</form>
</body>
</html>