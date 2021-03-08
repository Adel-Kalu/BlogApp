<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../shared/_layout.jsp" />

Article Title: ${article.title} <br>
Article Description: ${article.description} <br><br>

<div> Article Written By:  </div>

<c:forEach items="${article.getAuthors()}" var="author">
	
	<div> Author's Name: ${author.name}</div>
	<div> Author's Email Address: ${author.emailAddress} </div>
	
	<hr>
	
</c:forEach>