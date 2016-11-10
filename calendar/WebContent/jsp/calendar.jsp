<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<c:import url="/jsp/navbar.jsp"></c:import>
	<script type="text/javascript">
		$(function() {
			inicializar();
			cargarCalendar();
		});
	</script>
	
	
	
	<div class="container">
		
		<div class="form-group">
			<a href="eventoprivado.do" class="btn btn-primary">
				<span class="glyphicon glyphicon-plus" ></span> <fmt:message key="calendar.nuevoevento"/>
			</a>
			<a href="reunion.do" class="btn btn-primary">
				<span class="glyphicon glyphicon-plus" ></span> <fmt:message key="calendar.nuevareunion"/>
			</a>
			
			<div class="row text-right">
				<a href="anterior.do" class="btn btn-default">
					<span class="glyphicon glyphicon-menu-left" ></span>
				</a>
				<a href="calendar.do" class="btn btn-default">
					<span class="glyphicon glyphicon-calendar" ></span>
				</a>
				<a href="siguiente.do" class="btn btn-default">
					<span class="glyphicon glyphicon-menu-right" ></span> 
				</a>
			</div>
		</div>
		
		<jsp:useBean id="today" class="java.util.Date" scope="page" />
		<fmt:formatDate value="${today}" pattern="ddMMyyyy" var="hoy"/>
		
		<table class="table table-bordered text-center">
		<th class="text-center">#</th>
		<c:forEach items="${grilla.listaEventos}" var="grid">
	    	<th class="text-center">
	    		<fmt:formatDate pattern="EEEE" value="${grid.dia}" /><br/																																		>
	    		<fmt:formatDate pattern="dd/MM/yyyy" value="${grid.dia}" />
	    	</th>
		</c:forEach>
		<tr>
		<td>
			<c:forEach items="${horario}" var="hora">
				<div id="${fn:replace(hora,':','')}" style="height: 30px">${hora}</div>
			</c:forEach>
		</td>
		<c:forEach items="${grilla.listaEventos}" var="grid">
	    	<fmt:formatDate pattern="ddMMyyyy" value="${grid.dia}" var="dia"/>
	    	<c:if test="${dia == hoy}">
	    		<td class="wrapper-dia droppable" bgcolor="#CEF6EC">
	    	</c:if>
	    	<c:if test="${dia != hoy}">
	    		<td class="wrapper-dia droppable">
	    	</c:if>
	    		<div id="${dia}" class="dia-evento"></div>
	    	</td>
		</c:forEach>
		
		
		</tr>
		
		</table>
		
		
	</div>
</body>
</html>