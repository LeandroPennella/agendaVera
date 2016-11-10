<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<c:import url="/jsp/navbar.jsp"></c:import>

	<div class="container">

		<div class="panel-group">
		  	<div class="panel panel-primary">

			    <div class="panel-heading">
			    	<h2>${reunion.nombre}</h2>
			    </div>
		  		<div class="panel-body">
		  			<div class="col-lg-5">
			  			<h4>
				    		<fmt:formatDate pattern="EEEE" value="${reunion.fecha}" /> <fmt:formatDate pattern="dd/MM/yyyy" value="${reunion.fecha}" /><br/>
				    	</h4>
				    	<hr/>
				    	<p> 
				    		<strong><fmt:message key="detalle.horario" /></strong> 
				    		${reunion.inicio} - ${reunion.fin}
				    	</p>
				    	<hr/>
				    	<p> <strong><fmt:message key="detalle.temario" /></strong> ${reunion.temario} </p>
				    	<hr/>
				    	<p> <strong><fmt:message key="detalle.sala" /></strong> ${reunion.sala.nombre} </p>
		  			</div>
					<div class="col-lg-3 col-lg-offset-1">
						<h4><fmt:message key="reunion.estado.titulo"/></h4>		
						<div class="form-inline">
							<table class="table text-center">
								<th class="text-center"><fmt:message key="reunion.estado.titulo.usuario" /></th>
								<th class="text-center"><fmt:message key="reunion.estado.titulo.estado" /></th>
								
								<c:forEach items="${reunion.invitados}" var="invitado">
									<tr>
										<td>${invitado.usuario.username}</td>
										<td>
											<c:if test="${invitado.estado == 1}">
												<fmt:message key="reunion.estado.pendiente"/>
												<span class="glyphicon glyphicon-question-sign text-warning"></span>
											</c:if>
											<c:if test="${invitado.estado == 2}">
												<fmt:message key="reunion.estado.confirmado"/>
												<span class="glyphicon glyphicon-ok text-success"></span>
											</c:if>
											<c:if test="${invitado.estado == 3}">
												<fmt:message key="reunion.estado.rechazado"/>
												<span class="glyphicon glyphicon-remove text-danger"></span>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
			  	</div>
			</div>
		</div>
	</div>

</body>
</html>