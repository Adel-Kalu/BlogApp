
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../shared/_layout.jsp" />

<table class="table table-striped">

	<tr>
		<th>Author Name</th>
		<th>Email Address</th>
		<th>Gender</th>
		<th>Date of Birth</th>
		<!-- <th>Article</th> -->
		
		<% if(session.getAttribute("user") != null) { %>
		<th>Actions</th>
		<% } %>
	</tr>

	<c:forEach items="${authors}" var="author">
		<tr>
			<td><a href="${appName}author/detail?id=${author.id}">${author.name}</a></td>
			<td>${author.emailAddress}</td>
			<td>${author.gender}</td>
			<td>${author.dateofBirth}</td>
			<%-- <td>${author.article}</td> --%>

<% if(session.getAttribute("user") != null) { %>
			<td><a href="${appName}author/edit?id=${author.id}">Edit</a>
		<% if(session.getAttribute("userRole").equals("admin")) { %>	
			 | <a
				href="${appName}author/delete?id=${author.id}">Delete</a></td>
				
				
<% } %>				
<% } %>
		</tr>
	</c:forEach>
</table>