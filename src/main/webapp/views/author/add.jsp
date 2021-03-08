<jsp:include page="../shared/_layout.jsp" />

<form action="${appName}author/add" method="post">

	<div>
		<label>Author's Name </label> <input type="text" name="name">
	</div>

	<div>
		<label>Email Address </label> <input type="text" name="emailAddress">
	</div>

	<div>
		<label>Gender </label> <input type="text" name="gender">
	</div>

	<div>
		<label>Date of Birth </label> <input type="date" name="dateofBirth">
	</div>

	<div>
		<label>Article </label> <input type="text" name="article">
	</div>

	<button type="submit">Submit</button>

</form>