<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select a Product </title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/TablePageStyling.css"/>"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"
 type="text/javascript" charset="utf-8"></script>
 
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
</head>
<h1> Select a Product </h1>
<table class="table">
<tr>
<th>Product Name</th>
<th>Product Image</th>
<th>Quantity</th>
<th>Add to Cart</th>
<th>Add to Wishlist</th>
</tr>

<c:forEach var="line" items="${productList}">
<form:form  action="${contextPath}/customer/cart" commandName="cart"
		method="post">
		<tr>
		<td>
		<c:out value="${line.productName}"/>
		</td>
		<td>
		<img height="250" width="220" src="${line.filename}" class="img-rounded" />
		</td>
		
		<td>
   		<select name="quantity">
   		 <option>1</option>
   		 <option>2</option>
   		 <option>3</option>
   		 <option>4</option>
   		 <option>5</option>
 		</select>
 		</td>
 	
<input type="hidden" name="product" id="product" value="${line.productName}"/>
<td> 		
<input type="submit" class="btn btn-info" id="add_to_cart" name="selection" value="Add to Cart" /></td>
<td>
<input type="submit" class="btn btn-info" name="selection" value="Add to Wishlist" />
</td>
</tr>


</form:form>

</c:forEach>
</table>

<script type="text/javascript" charset="utf-8">
$(function() {
    $('#add_to_cart').click(function() {
    	alert("Item added to cart!");
    });
});
</script>


</body>



</html>