<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>E-commerce store</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/AdminStyle.css"/>"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>
<body>

	<!-- <a href="user/register.htm">Register a new User</a><br/>  -->

	<a href="user/register.htm" class="btn btn-info" role="button">Register a new User</a><br/>
	<h2>Login</h2>
	<form action="user/login" method="post">
	
		<table class="table">
		<tr>
		    <td>User Name:</td>
		    <td><input name="username" size="30" required="required" /></td>
		</tr>
		
		<tr>
		    <td>Password:</td>
		    <td><input type="password" name="password" size="30 required="required"/></td>
		</tr>
		
		<tr>
		    <td colspan="2"><input type="submit" class="btn btn-info" value="Login" /></td>
		</tr>
				
		</table>

	</form>


</body>
</html>

