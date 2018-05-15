<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/TablePageStyling.css"/>"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Update Product</title>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="javascript:history.go(-1)" class="btn btn-info">Go Back</a><br/>

	<h1>Product Update</h1>
    <div>
    <form:form action="${contextPath}/product/update/success" commandName="product"
		method="post" enctype="multipart/form-data">
	<table class="table">
	<tr>
	<th>
	Product Name
	</th>
	<th>
	Product Price
	</th>
	<th>
	Product Category
	</th>
	
	</tr>
    <tr>
    <td><input type="text" name="productName"value="${product.productName}"/></td>
    <td><input type="text" name="productPrice" value="${product.productPrice}" /></td>
    <td><select name="category" class="input-large">
   		 <c:forEach var="line" items="${categoryList}">
         <option><c:out value="${line.categoryName}"/></option>
   		 </c:forEach>
    </td>
        <tr>
		<td>
		Select photo:</td><td> <input type="file" name="photo"/></td></tr>
    <tr>
    <td>
    <input type="hidden" value="" name="filename"/>
    
    <input type="submit" name="action"class="btn btn-info" value="Update" />
   </td>
   </tr>
   </table>
    <input type="hidden" name="productID" class="btn btn-info" value="${product.productID}"/>
    
    </form:form>
    </div>
</body>
</html>