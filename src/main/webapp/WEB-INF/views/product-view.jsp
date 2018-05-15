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
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/TablePageStyling.css"/>"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="javascript:history.go(-1)" class="btn btn-info">Go Back</a><br/>

	<h1>Product View</h1>
	
	<div>
    

    
    <c:set var="pList" value="${requestScope.productList}"/>
	<table class="table">
    <thead>
    <tr>
    <td>Product Name</td>
    <td>Product Price</td>
    <td>Category Name</td>
    <td>Product ID</td>
    <td>Product Image</td>
    <td>Update Product</td>
    <td>Delete Product</td>
    
    
    </tr>
    </thead>
    
    
    
    <c:forEach items="${pList}" var="product">
    <form:form action="${contextPath}/employee/product/update" commandName="product"
		method="post">
    <tbody>
    <tr> 
    <td> <c:out value="${product.productName}"></c:out></td>
    <td> <c:out value="${product.productPrice}"></c:out></td>
    <td><c:out  value="${product.category.categoryName}"></c:out></td>
    <td><c:out value="${product.productID}"/></td>
    
    <td><img class="img-rounded"  height="150" width="150" src="${product.filename}" /></td>
    <td><input type="submit" class="btn btn-info" name=action value="Update Product" /></td>
    <td><input type="submit" class="btn btn-info" name="action" value="Delete" /></td>
    </tr>
    </tbody>
    <input type="hidden" name="productSelected" value="${product.productID}"/>
    </form:form>
    </c:forEach>
    </table>
    
    
   
    </div>
    
</body>


</html>