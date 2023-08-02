<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EditProfile</title>
</head>
<body>
	<form action="EditProfile" method="post">
		<h1 align="center">update Profile</h1>
		<%@ page import="com.zoho.model.*"%>
		<%@ page import="com.zoho.api.*"%>
		<%@ page import="com.zoho.servlet.*"%>
		<%@ page import="com.zoho.utility.*"%>
		<%@ page import="com.zoho.database.*"%>

		<%User user = ThreadLocalUtil.getCurrentUser();%>

		<label for="fname"><b>First Name </b></label> <input type="text"
			value="<%=user.getMap().get("FirstName")%> " name="fname"><br>

		<label for="lname"><b>Last Name </b></label> <input type="text"
			value="<%=user.getLastName()%>" name="lname"><br> <label
			for="gender"><b>Gender</b></label><br> <input type="radio"
			id="female" name="Gender" value="Female"> <label for="female">Female</label><br>

		<input type="radio" id="male" name="Gender" value="Male"> <label
			for="male">Male</label><br> <label>Date Of Birth</label> <input
			type="date" value="<%=user.getDateOfBirth()%>" name="dob"><br>

		<div class="clearfix">
			<!-- <button type="button" formaction="EditProfile">Submit</button> -->
			<input type="submit" value="submit">
		</div>
	</form>
</body>
</html>