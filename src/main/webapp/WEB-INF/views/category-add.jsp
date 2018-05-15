<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/RegistrationPageStyling.css"/>"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="javascript:history.go(-1)" class="btn btn-info">Go Back</a><br/>

	<h2>Register a New Category</h2>

	<form:form action="${contextPath}/category/add" commandName="category"
		method="post" enctype="multipart/form-data">

		<table class="table table-condensed">
		
			<tr>
				<td>Category Name:</td>
				<td><form:input path="categoryName" size="30" required="required" />
					<font color="red"></font></td>
			</tr>

		<tr>
		<td>
		Select photo: </td> <td><input type="file" name="photo"/></td></tr>
		
		<input type="hidden" value="" name="filename" />
		
		
		<tr>
				<td colspan="2"><input type="submit" value="Register Category" class="btn btn-info"/></td>
			</tr>
		</table>

	</form:form>




</body>
</html>