<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<c:import url="/jsp/navbar.jsp"></c:import>
	
	<script type="text/javascript">
		$(function() {
			inicializar();
			
			var lista = [];
			
			<c:forEach items="${reunion.invitados}" var="items">
				lista.push({estado: '${items.estado}', usuario: {id: '${items.usuario.id}', username: '${items.usuario.username}'}});
			</c:forEach>
			
			dibujarInvitados(lista);
		});
	</script>
	
	<div class="container" style="margin-top: -40px">
	<form:form method="POST" modelAttribute="reunion" action="validareunion.do" class="form" role="form">
		
		<div class="row">
            <div class="col-lg-10 col-lg-offset-1">
                <h1 class="page-header"><fmt:message key="reunion.header" /></h1>
            </div>
        </div>
		
		<div class="col-lg-6 col-lg-offset-1">		
			
			<div class="form-group">
			<fmt:message key="evento.nombre" var="phNombre"/>
			<form:input path="nombre" placeholder="${phNombre}" class="form-control "/>
			<form:errors path="nombre" cssStyle="color: red"/>
			</div>
				
		
			<div class="form-group">
				<fmt:message key="reunion.temario" var="phTemario"/>
				<form:textarea path="temario" placeholder="${phTemario}" rows="2" cols="30" class="form-control"/>
			</div>
			
			<div class="form-group">
				<div class="form-inline">
					<fmt:message key="evento.fecha" var="phFecha"/>
					<fmt:formatDate value="${reunion.fecha}" var="fechaReunion" pattern="dd/MM/yyyy" />
					<form:input id="datepicker" path="fecha" value="${fechaReunion}" placeholder="${phFecha}" class="form-control"/>
													
					<form:select path="inicio" class="form-control">
					    <form:options items="${horas}"/>
					</form:select>
					<form:errors path="inicio" cssStyle="color: red"/>
					
					<form:select path="fin" class="form-control">
					        <form:options items="${horas}"/>
					</form:select>
					<div class="form-group">
						<form:errors path="fecha" cssStyle="color: red"/>
						<br>
						<form:errors path="fin" cssStyle="color: red"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<form:select path="idSala" items="${reunion.salas}" itemValue="id" itemLabel="nombre" class="form-control"/>
				<form:errors path="idSala" cssStyle="color: red"/>
			</div>
			
			
			<div class="form-group">
				<fmt:message key="reunion.invitar" var="phInvitar"/>	
				<input id="txtAutocomplete" placeholder="${phInvitar}" class="form-control"/>
				<form:errors path="invitados" cssStyle="color: red"/>
				<div id="rtaInvitar"></div>
			</div>
			<br/><br/>
		</div>	
		
		<div class="col-lg-3 col-lg-offset-1">		
			<div id="tablaInvitados" ></div>
		</div>
		
		<div class="col-lg-12 text-center">
			<form:button class="btn btn-primary">
				<span class="glyphicon glyphicon-floppy-saved" aria-hidden="true"></span> <fmt:message key="boton.guardar"/>
			</form:button>
			<c:if test="${reunion.soyModificacion eq true}">
				<a href="borrarreunion.do" class="btn btn-danger">
					<span class="glyphicon glyphicon-trash" aria-hidden="true"></span> <fmt:message key="boton.borrar"/>
				</a>
			</c:if>
		</div>
	</form:form>
	</div>
	
	
	
</body>
</html>