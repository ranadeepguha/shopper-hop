<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/RegistrationPageStyling.css"/>"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>

</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="javascript:history.go(-1)" class="btn btn-info">Go Back</a><br/>

	<h1>Register a New Product</h1>

<div>

<form:form action="${contextPath}/product/add" commandName="product" method="post" enctype="multipart/form-data">

	<input type="hidden" value="" name="filename"/>
	
		<table class="table table-condensed">
		
			<tr>
				<td>Product Name:</td>
				<td><form:input path="productName" size="30" required="required" />
					<font color="red"></font></td>
			</tr>

			<tr>
				<td>Product Price:</td>
				<td><form:input path="productPrice" size="30" required="required" />
					<font color="red"></font></td>
			</tr>
	
		<tr>
		<td>
		Select photo:</td><td> <input type="file" name="photo"/></td></tr>
		
		<tr>
		<td>Select Category	</td>
		
		<td>
		 <select name="category"  class="input-small">
   		 <c:forEach var="line" items="${categoryList}">
         <option><c:out value="${line.categoryName}"/></option>
   		 </c:forEach>
 		</select>
 		</td>
 		
 		</tr>
		
			
		<tr>
			<td colspan="2"><input type="submit" value="Register Product" class="btn btn-info" /></td>
			</tr>
		</table>
	
	
	

	</form:form>
</div>

	

</body>
</html>