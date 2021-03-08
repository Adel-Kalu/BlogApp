<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../shared/_layout.jsp" />

<table class="table table-striped">

	<tr>
		<th>Article Title</th>
		<th>Article Description</th>

		<security:authorize access="isAuthenticated()">
			<th>Actions</th>
		</security:authorize>
	</tr>

	<c:forEach items="${articles}" var="article">
		<tr>
			<td><a href="${appName}article/detail?id=${article.id}">${article.title}</a></td>
			<td>${article.description}</td>

			<security:authorize access="isAuthenticated()">

				<td><a href="${appName}article/edit?id=${article.id}">Edit</a>

			<security:authorize access="hasRole('ADMIN')">
  					| <a href="${appName}article/delete?id=${article.id}">Delete</a></td>
			</security:authorize>

			</security:authorize>
		</tr>
	</c:forEach>
</table>