<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../shared/_layout.jsp" />

Author's Name: ${author.name} <br>
Email Address: ${author.emailAddress} <br>

Gender: ${author.gender} <br>
Date of Birth: ${author.dateofBirth} <br><br>

<%-- Article: ${author.article} <br> --%>

<table>
	<c:forEach items="${author.getArticles()}" var="article">
		<tr>
			<td> Title: ${article.title} </td>
		</tr>
		<tr>
			<td> Description: ${article.description} </td>
		</tr>
			
	</c:forEach>
</table>