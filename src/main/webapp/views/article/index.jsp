
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../shared/_layout.jsp" />

<table class="table table-striped">

	<tr>
		<th>Article Title</th>
		<th>Article Description</th>
		<th>Actions</th>
	</tr>

	<c:forEach items="${articles}" var="article">
		<tr>
			<td><a href="${appName}article/detail?id=${article.id}">${article.title}</a></td>
			<td>${article.description}</td>

			<td><a href="${appName}article/edit?id=${article.id}">Edit</a> | <a
				href="${appName}article/delete?id=${article.id}">Delete</a></td>
		</tr>
	</c:forEach>
</table>