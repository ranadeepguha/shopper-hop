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
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/TablePageStyling.css"/>"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>

<h2>Search Results</h2>

<c:choose>
<c:when test="${resultSize == 0 }">
<br>No Results Found!<br>
</c:when>
<c:otherwise>


<table class="table">
<c:forEach var="line" items="${productList}">
<form:form action="${contextPath}/customer/cart" commandName="cart"
		method="post">
		<tr>
		<td>
		<c:out value="${line.productName}"/>
		</td>
		<td>
		<img height="150" width="150" src="${line.filename}" class="img-rounded"  />
		</td>
		
		<td>
   		<select name="quantity" class="input-small">
   		 <option>1</option>
   		 <option>2</option>
   		 <option>3</option>
   		 <option>4</option>
   		 <option>5</option>
 		</select>
 		</td>
 	
<input type="hidden" name="product" value="${line.productName}"/>
<td> 		
<input type="submit" name="selection" value="Add to Cart" class="btn btn-info" /></td>
<td>
<input type="submit" name="selection" value="Add to Wishlist" class="btn btn-info"/>
</td>
</tr>
</form:form>

</c:forEach>
</table>

</c:otherwise>
</c:choose>








<!--  
<form:form action="${contextPath}/customer/cart" commandName="cart"
		method="post">
		<select name="product">
		
   		 <c:forEach var="line" items="${productList}">
         <c:out value="${line.productName}"/>
   		 </c:forEach>
 		
 		</select>
 		
 		 <c:forEach var="line" items="${productList}">
         <c:out value="${line.productName}"/>
   		<td> <img height="150" width="150" src="${line.filename}" /></td>
   			
   		 </c:forEach>
 		
 		
 		<select name="quantity">
   		 <option>1</option>
   		 <option>2</option>
   		 <option>3</option>
   		 <option>4</option>
   		 <option>5</option>
 		</select>
 		
 <input type="submit" value="Add to Cart" />
</form:form>-->


</body>


</html>