
<!DOCTYPE html>
<html>
<head>
<title>Email Management</title>
<script>
        function deleteEmail(emailId) {
        	var email=emailId;
        	document.getElementById("emailId").value = email;
        	document.getElementById("editemail").submit();
        }
        function setPrimary(emailId) {
        	var email=emailId;
        	document.getElementById("emailId").value = email;
        	document.getElementById("editemail").submit();
        }
  </script>
    
</head>
<body>
	<form id="editemail" method="post" action ="EditEmail">
		
		<h1>Email Details</h1>
		<%@page import="com.zoho.model.*"%>
		<%@page import="com.zoho.servlet.*"%>
		<%@page import="com.zoho.api.*"%>
		<%@page import="apiInterFace.*"%>
		<%@page import="com.zoho.utility.*"%>
		<%@page import="proxy.*"%>
		<%@page import="java.util.ArrayList"%>

		<%
		

		User user =ThreadLocalUtil.getCurrentUser();
			ArrayList<Email> emailList = ProxyFactory.getEmailProxy().getAllEmail(user.getUserId());

		%>

		<h3>
			User:
			<%=user.getFirstName()%>
			<%=user.getLastName()%></h3>

		<h4>Emails:</h4>

		<%for (Email email : emailList) {%>
			<p><%=email.getEmailId()%></p><button onclick="deleteEmail('<%= email.getEmailId()%>')" name="button" value="deleteEmail">Delete</button>
		
			<%if (email.isPrimaryEmail()==false){ %>
			
				<button   onclick="setPrimary('<%= email.getEmailId()%>')" name="button" value="setAsPrimary">Set as Primary</button>

			<%	} %>
			 <input type="hidden" id="emailId" name="emailId">
		<%  } %>
		
		
		<h4>Add New Email:</h4>

		<input type="email" name="newEmail" placeholder="Email Address">
		<button type="submit" name="button" value="addEmail">Add</button><br>
		
		<input formaction="LogoutServlet" type="submit" value="Logout">
	</form>
</body>
</html>