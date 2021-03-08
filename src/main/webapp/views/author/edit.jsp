<jsp:include page="../shared/_layout.jsp" />

<form action="${appName}author/add" method="post">

	<div>
		<label>Author's Name </label> <input type="text" name="name" value="${author.name}">
	</div>

	<div>
		<label>Email Address </label> <input type="text" name="emailAddress"  value="${author.emailAddress}">
	</div>

	<div>
		<label>Gender </label> <input type="text" name="gender"  value="${author.gender}">
	</div>

	<div>
		<label>Date of Birth </label> <input type="date" name="dateofBirth"  value="${author.dateofBirth}">
	</div>

	<div>
		<label>Article </label> <input type="text" name="article"  value="${author.article}">
	</div>
	
	<input name="id" type="hidden" value="${author.id}">
	

	<button type="submit">Submit</button>

</form>