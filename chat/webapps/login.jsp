<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class ="loginbox">
	<h2>Login</h2>
	<form action ="login" method = "POST">
	<div class = "inputbox">
		<input type= "email"  name ="EmailId" required>
			<label>Email</label>
	</div>
	<div class = "inputbox">
		<input type ="password"  name ="Password" required>
			<label>Password</label>
	</div>
	<div>
		<button type ="submit" class ="submit">Login</button>
	</div>
	<div class = "forgot-password">
		<a href ="#">Forgot Password</a>
	</div>
	
	<div class ="signUplink">
		<a href ="Signup.html">Don't have a account? <span class = "signup">SignUp Here</span></a>
	</div>
	
	
	</form>
	</div> 
</body>
</html>