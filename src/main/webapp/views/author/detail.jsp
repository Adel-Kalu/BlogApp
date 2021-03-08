<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../shared/_layout.jsp" />

Author's Name: ${author.name} <br>
Email Address: ${author.emailAddress} <br>

Gender: ${author.gender} <br>
Date of Birth: ${author.dateofBirth} <br><br>

<%-- Article: ${author.article} <br> --%>

	<c:forEach items="${author.getArticles()}" var="article">
			<div>Title: ${article.title} </div> 

			<div> Description: ${article.description} </div> 
			
			<hr>
	</c:forEach>
