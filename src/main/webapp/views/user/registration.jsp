<jsp:include page="../shared/_layout.jsp" />


<form action="${appName}user/registration" method="post">

	<div class="form-group">
	<label> First Name </label>
	<input name="firstName" type="text" class="form-control">
	</div>
	
	<div class="form-group">
	<label> Last Name </label>
	<input name="lastName" type="text" class="form-control">
	</div>
	
	<div class="form-group">
	<label> Email Address </label>
	<input name="emailAddress" type="email" class="form-control">
	</div>
	
	<div class="form-group">
	<label> Password </label>
	<input name="password" type="password" class="form-control">
	</div>
	
	<div class="form-group">
		<label>Select Role</label>
		<select name="userRole" class="form-control">
			<option value="ROLE_ADMIN">Admin</option>
			<option value="ROLE_USER">User</option>
		</select>
	</div>
	
	<br>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<button type="submit" class="btn btn-primary">Submit</button>

</form>