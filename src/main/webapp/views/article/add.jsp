<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../shared/_layout.jsp" />

<form action="${appName}article/add" method="post">

	<div class="form-group">
		<label>Article Title </label> <input type="text" name="title" class="form-control">
	</div>

	<div class="form-group">
		<label>Article Description </label> <input type="text" name="description" class="form-control">
	</div>

	<div class="form-group">
		<label>Author</label>
		<select name="author" class="form-control">
			<c:forEach items="${authors}" var="author">
				<option value="${author.id}"> ${author.name} </option>
			</c:forEach>	
		</select>
	</div>

	<button type="submit" class="btn btn-primary">Submit</button>

</form>