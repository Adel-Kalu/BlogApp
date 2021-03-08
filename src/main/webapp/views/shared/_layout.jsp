<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Blog APP</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="stylesheet" href="../css/myStyles.css">
<link rel="stylesheet" href="css/myStyles.css">

</head>
<body>
<h2>${Welcome}</h2>

<%-- ${pageContext["request"].userPrinciple.principal}
 --%>
 
<security:authorize access="isAuthenticated()">
<%-- ${pageContext.request.userPrincipal.name} --%>
<%-- <security:authentication property="principal.authorities" /> --%>
<security:authentication property="principal.id" />
<a href="${appName}">Home</a>

<a href="${appName}author/add">Add Author</a>
<a href="${appName}author/index">Author</a>

<a href="${appName}article/add">Add Article</a>
<a href="${appName}article/index">Article</a>

<div style="text-align:right; float:right;">

<b>Hi: 
<a href="${appName}user/profile?id=<security:authentication property="principal.id" />">
<security:authentication property="principal.username" /> </b>
</a>

<a href="${appName}logout">Logout</a>
</div>
 </security:authorize>
 
 
 <security:authorize access="!isAuthenticated()">
<a href="${appName}">Home</a>

<a href="${appName}author/index">Author</a>

<a href="${appName}article/index">Article</a>

<div style="text-align:right; float:right;">

<a href="${appName}login">Login</a>
<a href="${appName}user/registration">Create New Account</a>
</div>

</security:authorize>

<hr>

<c:if test="${message != null}">

<div class="alert alert-primary fade show" role="alert">
${message}
</div>
<% session.removeAttribute("message"); %>
</c:if>


<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$('.fade').slideUp(4000);
	</script>

</body>
</html>