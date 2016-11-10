<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	
	<c:import url="/jsp/navbar.jsp"></c:import>
	
	<c:if test="${existeusuario eq false}" >
		<div class="alert alert-danger fade in text-center">
		    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		    <p><fmt:message key="login.error.noexiste" /></p>
	  	</div>
  	</c:if>
  	
	
	<div class="col-md-4 col-lg-offset-4">
		<form:form method="POST" commandName="login" action="validalogin.do" class="form-signin">
		
		<div class="panel panel-primary">
	        <div class="panel-heading text-center">
				<img id="profile-img" class="img-circle" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
			</div>
	        
	        <div class="panel-body">
				<span id="reauth-email" class="reauth-email"></span>
				<fmt:message key="login.ph.usuario" var="phUsuario"/>
				<form:input path="username" id="inputEmail" class="form-control input-lg" placeholder="${phUsuario}" required="true" autofocus="true"/>
				<form:errors path="username" cssStyle="color: red"/>
				<br/>
				<fmt:message key="login.ph.password" var="phPassword"/>
				<form:input path="password" type="password" id="inputPassword" class="form-control input-lg" placeholder="${phPassword}" required="true"/>
				<form:errors path="password" cssStyle="color: red"/>
				<div id="remember" class="checkbox">
				    <label>
				        <form:checkbox path="recordar" /> <fmt:message key="login.recordar" />
				    </label>
				</div>
				<button class="btn btn-lg btn-primary btn-block btn-signin" type="submit"><fmt:message key="login.ingresar" /></button>
		    </div>
		</div>
		
		</form:form>
	 </div>	
	
	
	
	

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>	
	
		
</body>
</html>