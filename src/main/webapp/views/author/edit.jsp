<jsp:include page="../shared/_layout.jsp" />

<form action="${appName}author/add" method="post">

	<div class="form-group">
		<label>Author's Name </label> <input type="text" name="name" value="${author.name}" class="form-control">
	</div>

	<div class="form-group">
		<label>Email Address </label> <input type="text" name="emailAddress"  value="${author.emailAddress}" class="form-control">
	</div>

	<div class="form-group">
		<label>Gender </label> <input type="text" name="gender"  value="${author.gender}" class="form-control">
	</div>

	<div class="form-group">
		<label>Date of Birth </label> <input type="date" name="dateofBirth"  value="${author.dateofBirth}" class="form-control">
	</div>

<%-- 	<div>
		<label>Article </label> <input type="text" name="article"  value="${author.article}">
	</div> --%>
	
	<input name="id" type="hidden" value="${author.id}">
	
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<button type="submit">Submit</button>

</form>