<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<c:import url="/jsp/navbar.jsp"></c:import>
	
	<script type="text/javascript">
		$(function() {
			inicializar();
		});
	</script>
	
	<div class="container">
	<form:form method="POST" modelAttribute="evento" action="validaeventoprivado.do" class="form" role="form">
		
		<div class="row">
            <div class="col-lg-10 col-lg-offset-1">
                <h1 class="page-header"><fmt:message key="eventoprivado.header" /></h1>
            </div>
        </div>
		
		
		<div class="col-lg-6 col-lg-offset-1">		
			
			<div class="form-group">
			<fmt:message key="evento.nombre" var="phNombre"/>
			<form:input path="nombre" placeholder="${phNombre}" class="form-control "/>
			<form:errors path="nombre" cssStyle="color: red"/>
			</div>
			
			<div class="form-group">
				<fmt:message key="eventoprivado.descripcion" var="phDescripcion"/>
				<form:textarea path="descripcion" placeholder="${phDescripcion}" rows="5" cols="30" class="form-control"/>
			</div>
			
			<div class="form-group">
				<fmt:message key="eventoprivado.direccion" var="phDireccion"/>
				<form:input path="direccion" placeholder="${phDireccion}" class="form-control"/>
				<form:errors path="direccion" cssStyle="color: red"/>
			</div>	
			
			<div class="form-inline">
				<fmt:message key="evento.fecha" var="phFecha"/>	
				<fmt:formatDate value="${evento.fecha}" var="fechaEvento" pattern="dd/MM/yyyy" />
				<form:input id="datepicker" path="fecha" value="${fechaEvento}" placeholder="${phFecha}" class="form-control"/>
								
				<form:select path="inicio" class="form-control">
				    <form:options items="${horas}"/>
				</form:select>
				<form:errors path="inicio" cssStyle="color: red"/>
				
				<form:select path="fin" class="form-control">
					<form:options items="${horas}"/>
				</form:select>
				<br>
				<form:errors path="fecha" cssStyle="color: red"/>
				<br>
				<form:errors path="fin" cssStyle="color: red"/>
			</div>
			</br>
		</div>
		
		<div class="col-lg-12 text-center">
			<form:button class="btn btn-primary">
				<span class="glyphicon glyphicon-floppy-saved" aria-hidden="true"></span> <fmt:message key="boton.guardar"/>	
			</form:button>
			<c:if test="${evento.soyModificacion eq true}">
				<a href="borrarevento.do" class="btn btn-danger">
					<span class="glyphicon glyphicon-trash" aria-hidden="true"></span> <fmt:message key="boton.borrar"/>
				</a>
			</c:if>
		</div>
	</form:form>
	</div>
	
	
</body>
</html>