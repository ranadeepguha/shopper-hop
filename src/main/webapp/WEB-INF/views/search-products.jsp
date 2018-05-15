<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
      <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function validateForm() {
                var keyword = document.forms["myForm"]["keyword"].value;
                if (keyword == "") {
                    alert("Please enter a keyword and select a radio button");
                    return false;
                }
            }
        </script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/RegistrationPageStyling.css"/>"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
    
    </head>
    <body>
        <h2>Search Products</h2>
         <form:form action="${contextPath}/customer/productSearch" onsubmit="return validateForm()"
		method="post">
            Keyword <input type="text" name="keyword" ><br>
            <input type="submit" class="btn btn-info" value="Search Products">
		</form:form>
         </body>
</html>
