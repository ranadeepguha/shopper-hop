<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add User Form</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/RegistrationPageStyling.css"/>"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="javascript:history.go(-1)" class="btn btn-info">Go Back</a><br/>
	
	<h1>Register a New Employee</h1>

<div>
	<form:form action="${contextPath}/user/register" commandName="user"
		method="post">

		<table class="table">
			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" size="30" required="required" />
					<font color="red"><form:errors path="firstName" /></font></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" size="30" required="required" />
					<font color="red"><form:errors path="lastName" /></font></td>
			</tr>


			<tr>
				<td>User Name:</td>
				<td><form:input path="username" size="30" required="required" />
					<font color="red"><form:errors path="username" /></font></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><form:password path="password" size="30"
						required="required" /> <font color="red"><form:errors
							path="password" /></font></td>
			</tr>

			<tr>
				<td>Email Id:</td>
				<td><form:input path="email" size="30"
						type="email" required="required" /> <font color="red"><form:errors
							path="email" /></font></td>
			</tr>
			
		
			
			

			<tr>
				<td colspan="2"><input type="submit" value="Register User" class="btn btn-info"/></td>
			</tr>
		</table>
		
			<input type="hidden" name="role" value="Employee"/>
		

	</form:form>
</div>
</body>
</html>