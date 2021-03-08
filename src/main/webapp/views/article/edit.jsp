<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../shared/_layout.jsp" />

<form action="${appName}article/add" method="post">

	<div class="form-group">
		<label>Article Title </label> <input type="text" name="title" value="${article.title}" class="form-control">
	</div>

	<div class="form-group">
		<label>Article Description </label> <input type="text" name="description" value="${article.description}" class="form-control">
	</div>

	<div class="form-group">
		<label>Author</label>
		<select name="authors" class="form-control" multiple="multiple">
			<c:forEach items="${authors}" var="author">
				<option value="${author.id}"> ${author.name} </option>
			</c:forEach>	
		</select>
	</div>
	
	<input type="hidden" name="id" value="${article.id}">

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<button type="submit">Submit</button>

</form>