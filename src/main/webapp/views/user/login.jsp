<jsp:include page="../shared/_layout.jsp"></jsp:include>

<b>${message}</b>
<br>

<form action="${appName}user/login" method="post">

	<div class="form-group">
	<label> Email Address </label>
	<input name="emailAddress" type="email" class="form-control">
	</div>
	
	<div class="form-group">
	<label> Password </label>
	<input name="password" type="password" class="form-control">
	</div>
	
	<br>
	
	<button type="submit" class="btn btn-primary">Submit</button>

</form>