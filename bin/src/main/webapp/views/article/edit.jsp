<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../shared/_layout.jsp" />

<form action="${appName}article/add" method="post">

	<div>
		<label>Article Title </label> <input type="text" name="title" value="${article.title}">
	</div>

	<div>
		<label>Article Description </label> <input type="text" name="description" value="${article.description}">
	</div>

	<div>
		<label>Author</label>
		<select name="author">
			<c:forEach items="${authors}" var="author">
				<option value="${author.id}"
				
				<c:if test="${article.author.id == author.id}"> 
				selected="selected"
				</c:if>
				
				> ${author.name} </option>
			</c:forEach>	
		</select>
	</div>
	
	<input type="hidden" name="id" value="${article.id}">

	<button type="submit">Submit</button>

</form>