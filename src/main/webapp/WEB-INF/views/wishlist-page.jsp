<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/TablePageStyling.css"/>"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>
<body>
<h2>Welcome to your Wishlist, ${user.firstName} </h2><br><br>


<c:choose>
<c:when test="${listSize == 0 }">
<br>No Items in the Wishlist<br>
</c:when>
<c:otherwise>

<table class="table table-striped">
<tr>
<th>Product</th>
<th>Add to Cart</th>
<th>Delete From Wishlist</th>

</tr>

<c:forEach items="${addedList}" var="wishlist">
    <form:form action="${contextPath}/customer/wishlist/update" commandName="wishlist"
		method="post">
    
    <tr> 
    
    <td><c:out value="${wishlist.product.productName}" />
    
    <input type="hidden" name="wishlistID" value="${wishlist.wishlistID}"> 
    <input type="hidden" name="userID" value="${wishlist.userID}">
    <input type="hidden" name="productID" value="${wishlist.product.productID}">
    
    
    <td><input type="submit" name="action" class="btn btn-info" value="Add to Cart" /></td>     
    <td><input type="submit" name="action" class="btn btn-info" value="Delete" /></td>
   	
    </tr>
    
    </form:form>
    </c:forEach>
</table>

<br>
<br>


</c:otherwise>
</c:choose>
</body>


</html>