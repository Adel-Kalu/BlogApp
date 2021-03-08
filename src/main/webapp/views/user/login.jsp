<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../shared/_layout.jsp"></jsp:include>

<b>${message}</b>
<br>

<!-- To display bad credentials message -->
  <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
      <font color="red">
        Your login attempt was not successful due to ${SPRING_SECURITY_LAST_EXCEPTION.message}
      </font>
    </c:if>
      
<form action="${appName}login" method="post">

    
	<div class="form-group">
	<label> Email Address </label>
	<input name="username" type="email" class="form-control">
	</div>
	
	<div class="form-group">
	<label> Password </label>
	<input name="password" type="password" class="form-control">
	</div>
	
	<br>
	
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<button type="submit" class="btn btn-primary">Submit</button>

</form>