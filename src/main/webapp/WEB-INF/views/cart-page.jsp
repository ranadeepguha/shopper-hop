<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart Page</title>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/TablePageStyling.css"/>"/>

</head>
<body>
<h2>${user.firstName}'s Cart </h2>
<br><br>

<c:choose>
<c:when test="${size == 0 }">
No Items in the Cart
</c:when>
<c:otherwise>

<table class="table table-striped">
<tr>
<td>Product</td>
<td>Quantity</td>
</tr>
<c:forEach items="${addedCart}" var="cart">
    <form:form action="${contextPath}/customer/cart/update" commandName="cart"
		method="post">
    
    <tr> 
    
    <td><c:out value="${cart.product.productName}" />
    
    <td> <input type="text" name="quantity" value="${cart.quantity}" />
    
    <input type="hidden" name="cartID" value="${cart.cartID}"> 
    <input type="hidden" name="userID" value="${cart.userID}">
    <input type="hidden" name="productID" value="${cart.product.productID}">
        
    <td><input type="submit" class="btn btn-info" name=action value="Update Quantity" /></td>
    <td><input type="submit" class="btn btn-info" name="action" value="Delete" /></td>
   	
    </tr>
    
    </form:form>
    </c:forEach>
</table>

<br>
<br>
<div>
<form:form action="${contextPath}/customer/placeorder" commandName="cart"
		method="post">
Item Total:		$${itemTotal}<br>
Shipping Charges: $${shipping}<br>
Taxes Applied: ${taxes}%<br>
<br>
Order Total: $${orderTotal} 


<br><br>
<input type="hidden" name="itemTotal" value="${itemTotal}">
<input type="hidden" name="shipping" value="${shipping}">
<input type="hidden" name="taxes" value="${taxes}">
<input type="hidden" name="orderTotal" value="${orderTotal}">
<input type="submit" name="placeOrder" class="btn btn-info" value="Place Order"/>
</form:form>
</div>

</c:otherwise>
</c:choose>
</body>


</html>