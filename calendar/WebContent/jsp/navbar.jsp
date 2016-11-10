<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/jquery-ui.css" />" rel="stylesheet">
    <link href="<c:url value="/css/estilo.css" />" rel="stylesheet">
    <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
    <script src='<c:url value="/js/jquery.js" />'></script>
    <script src='<c:url value="/js/bootstrap.min.js" />'></script>
    <script src='<c:url value="/js/jquery-ui.js" />'></script>
	<script src='<c:url value="/js/jquery-ui.min.js" />'></script>
	
    
   <style>
  	.resizable p { text-align: center; margin: 0; }
  </style>
    
	<script type="text/javascript">
	  	
		
		function dibujarInvitados(lista)
		{
		  	var respuesta = "<table class='table text-center'> <thead><th colspan='3' class='text-center'>Lista de Invitados</th></thead>";
		  	
			for(i=0; i<lista.length; i++)
			{
				respuesta += "<tr><td>"; 
				respuesta += "<strong>"+lista[i].usuario.username+"</strong>";
				respuesta += "</td><td>";
				
				var estado;
				
				switch(String(lista[i].estado)){
					case "1": estado = "<fmt:message key='reunion.estado.pendiente'/> <span class='glyphicon glyphicon-question-sign text-warning'></span>"; break;
					case "2": estado = "<fmt:message key='reunion.estado.confirmado'/> <span class='glyphicon glyphicon-ok text-success'></span>"; break;
					case "3": estado = "<fmt:message key='reunion.estado.rechazado'/> <span class='glyphicon glyphicon-remove text-danger'></span>"; break;
				}
				
				respuesta += estado;
				respuesta += "</td><td>"; 
				respuesta += "<button type='button' class='btnBorrar btn btn-danger' name='"+lista[i].usuario.id+"'><span class='glyphicon glyphicon-remove' aria-hidden='true'></span></button>";
				respuesta += "</td></tr>";
			}
			respuesta += "</table>";
			
			$("#tablaInvitados").html(respuesta);
		}
	
		
		function getListaEventos()
		{
			var listaEventos = []; 
			
			'<c:forEach items="${grilla.listaEventos}" var="grid">'
				'<c:forEach items="${grid.eventos}" var="eve">'
					var evento = {}
					evento.id = '${eve.id}';
					evento.nombre = '${eve.nombre}';
					evento.dia = '<fmt:formatDate pattern="ddMMyyyy" value="${grid.dia}"/>';
					evento.inicio = '${eve.inicio}';
					evento.fin = '${eve.fin}';
					evento.estado = '${eve.estadoInvitacion}';
					evento.soyowner = '${eve.soyOwner}';
					evento.soyreunion = '${eve.soyReunion}';
										
					listaEventos.push(evento);
				'</c:forEach>'
			'</c:forEach>'
						
			return listaEventos;
			
		}
		
		
		function getHorarioPosicion()
		{
			var listaHorario = []; 
			
			'<c:forEach items="${horario}" var="hora" varStatus="i">'
				var horario = {}
				horario.hora = '${hora}';
				var indice = '${i.index}';
				horario.posicion = parseInt(indice);
				listaHorario.push(horario);
			'</c:forEach>'
			
			return listaHorario;
		}
		
		
		
		function calcularPosicionEnGrilla(hora)
		{
			listaHorario = getHorarioPosicion();
			
			for(var i=0; i< listaHorario.length; ++i)
			{
				var horario = listaHorario[i];
				
				if(horario.hora.replace(":","") == hora)
				{
					return horario.posicion * 30;
				}
			}
		}
		
		
		
		function calcularHora(pixel)
		{
			listaHorario = getHorarioPosicion();
			
			for(var i=0; i< listaHorario.length; ++i)
			{
				var horario = listaHorario[i];
				var px = horario.posicion * 30;
				if(px == pixel)
				{
					return horario.hora;
				}
			}
		}
		
		
		function getColorEvento(evento)
		{
			var color;
			
			if(evento.soyowner == "true")
			{
				color = "info";
				
				if(evento.soyreunion == "true")
				{
					color = "success";
				}	
			}
			else{
				switch(evento.estado){
					case "1": color = "warning"; break;
					case "2": color = "success"; break;
					case "3": color = "danger"; break;
				}
			}
			
			return color;
		}
		
		
		function getUrl(evento)
		{
			var url;
			if(evento.soyowner == "true")
			{
				if(evento.soyreunion == "true")
				{
					return url = "reunion.do?idReunion="+evento.id;
				}else
				{
					return url = "eventoprivado.do?id="+evento.id;				
				}
			}
			else{
				switch(evento.estado){
					case "1": return url = "confirmar.do?id="+evento.id;
					case "2": return url = "visualizar.do?id="+evento.id;
					case "3": return url = "visualizar.do?id="+evento.id;
				}
			}
		}
		
		
		function getClase(evento)
		{
			if(evento.soyowner == "true")
			{
				return "evento";
			}
			else{
				return "no-evento"
			}
		}
		
		
		
		
		function actualizarEvento(idEvento, horaInicio, horaFin)
		{
			var evento = {};
			evento.id = idEvento;
			evento.inicio = horaInicio;
			evento.fin = horaFin;
			
			$.ajax({
				url : '<c:url value="/updatehorario.do" />', 
				type : "POST", 
				data:  JSON.stringify(evento), 
				dataType : "json",
				contentType : "application/json;charset=UTF-8",
				
				success : function(results, status, xhr){
					dibujarGrilla(results);
				},
			});	
		}
		
		
		function dibujarEvento(evento)
		{
			var idDiv = evento.dia.replace(":","");
			var top = calcularPosicionEnGrilla(evento.inicio.replace(":",""));
			var height = (calcularPosicionEnGrilla(evento.fin.replace(":","")) - top) + 30;
			var url = getUrl(evento);
			var color = getColorEvento(evento);
			var clase = getClase(evento);
			evento.height = height;
			
			var elemento="<div id='"+evento.id+"' class='"+clase+" label label-"+color+"' style='top: "+top+"px; height: "+height+"px; display: flex;'>";
			elemento+="<div class='evento-detalle text-left' >";
			elemento+="<a href='"+url+"'>";
			elemento+=evento.nombre;
			elemento+="<div class='horario-evento'>";
			elemento+="<div class='hora-inicio'> "+evento.inicio+"</div>";
			elemento+="<div class='separador-hora'> / </div>";
			elemento+="<div class='hora-fin'> "+evento.fin+"</div>";
			elemento+="</div>";
			elemento+="</a>";
			elemento+="</div>";
			elemento+="</div>";
			
			$("table td #"+idDiv).append(elemento);
		}
		
		
		
		function dibujarGrilla(listaEventos)
		{
			for(i=0; i<listaEventos.length; i++)
			{
				var evento = listaEventos[i];
				dibujarEvento(evento);
			}
			
			var matrizEventos = organizarEventos(listaEventos);
			redimensionarEventos(matrizEventos, null);
			
			$(".evento").draggable({ 
				axis: "y",
				grid: [ 30, 30 ],
				containment: ".wrapper-dia",
				stack: ".evento",
				drag: function( event, ui ) {
					var top = $(this).css("top").split("px")[0];
					var height = $(this).css("height").split("px")[0];
					var inicio = calcularHora(top);
					var fin = calcularHora(parseInt(top) + parseInt(height) - parseInt(30));
					$(this).find("div").find("a").find(".hora-inicio").html(inicio);
					$(this).find("div").find("a").find(".hora-fin").html(fin);
				}
			});
			
			$(".droppable").droppable({ 
				drop: function( event, ui ) {
					var elemento = ui.draggable;
					var top = elemento.css("top").split("px")[0];
					var height = elemento.css("height").split("px")[0];
					var idEvento = elemento.attr("id");
					var inicio = calcularHora(top);
					var fin = calcularHora(parseInt(top) + parseInt(height) - parseInt(30));
					var matrizEventos = organizarEventos(listaEventos);
					redimensionarEventos(matrizEventos, idEvento);
					actualizarEvento(idEvento, inicio, fin);
				}		
			});
		}
		
		
		function redimensionarEventos(matrizEventosSolapados, idEvento)
		{
			for(i=0; i<matrizEventosSolapados.length; i++)
			{
				listaEventoSolapados = matrizEventosSolapados[i];

				for(j=0; j<listaEventoSolapados.length; j++)
				{
					var e = listaEventoSolapados[j];
					var divEvento = $("#"+e.id);
					var margen = j*8;
					var width = 85-margen;
					divEvento.css("z-index", j+1);
					divEvento.width(width+"%");
					
					if(idEvento != e.id)
					{
						divEvento.css("left", margen+"%");
					}
					else{
						divEvento.animate({
							left: margen+"%",
						}, "fast")
					}
				}
			}
		}
			
			
		function cargarCalendar(){
			var height = $(".wrapper-dia").height();
			$(".dia-evento").height(height);		
			dibujarGrilla(getListaEventos());
		}
		
		
		function limpiarUndefined(someArray)
		{
			someArray = someArray.filter(function( element ) {
				   return !!element;
				});
			return someArray;
		}
		
		
		function organizarEventos(listaEventos)
		{
			var matrizEventosSolapados = new Array();
			
			for(i=0; i<listaEventos.length; i++)
			{
				var evActual = listaEventos[i];
				var idEvento = "#"+evActual.id;
				var actualTop = $(idEvento).offset().top;
				var actualHeight = actualTop + $(idEvento).height();
				var arrEventosSolapados = new Array();
				
				arrEventosSolapados.push(evActual);
				
				for(j=0; j<listaEventos.length; j++)
				{
					var evComparar = listaEventos[j];
					var idEvComparar = "#"+listaEventos[j].id;
					
					if(evActual.id != evComparar.id && evActual.dia == evComparar.dia)
					{
						var compararTop = $(idEvComparar).offset().top;
						var compararHeight = compararTop + $(idEvComparar).height();
						
						var TopDentro = (compararTop >= actualTop && compararTop <= actualHeight);
						var HeightDentro = (compararHeight >= actualTop && compararHeight <= actualHeight);
						var DivEnteroDentro = (actualTop >= compararTop  && actualHeight <= compararHeight); 
												
						if( TopDentro || HeightDentro  || DivEnteroDentro )
						{							
							arrEventosSolapados.push(evComparar);
						}
					}
				}
				
				arrEventosSolapados.sort(function (elementoA, elementoB) {
					return parseInt(elementoB.height) - parseInt(elementoA.height);
				});
				
				matrizEventosSolapados.push(arrEventosSolapados);
			}
			
			matrizEventosSolapados = eliminarDuplicados(matrizEventosSolapados);		
			matrizEventosSolapados = limpiarUndefined(matrizEventosSolapados);
			
			return matrizEventosSolapados;
		}
		
		
		function calcularTamañoDelEvento(evento)
		{
			var idEvento = "#"+evento.id;
			var Top = $(idEvento).offset().top;
			var Height = $(idEvento).height();
			return Top + Height - parseInt(30);	
		}
		
		
		function eliminarDuplicados(matriz)
		{
			for(i=0; i<matriz.length; i++)
			{
				array = matriz[i];
				
				for(j=0; j<matriz.length; j++)
				{
					if(i!=j && JSON.stringify(matriz[i]) == JSON.stringify(matriz[j]))
					{
						delete matriz[j];
					}
				}
			}
			
			return matriz;
		}
		
		
		
		function inicializar() {
		  
		  $('#datepicker').datepicker({ dateFormat: 'dd/mm/yy' }).val();
		  
		  $(document).on('click', '.btnBorrar', function() {
				var value = $(this).attr('name');
				var usuario = {}
				usuario.id = value 
				$.ajax({
					url : '<c:url value="/quitarinvitado.do" />',
					type : "POST",
					data:  JSON.stringify(usuario), 
					dataType : "json",
					contentType : "application/json;charset=UTF-8",
					
					success : function(results, status, xhr){
						dibujarInvitados(results);
					},
				});
			}
		  );
		  
		  
		  $("#txtAutocomplete").autocomplete({

				source: function(request, response){
					
					var criterio = request.term;
					
					$.ajax({
						url : '<c:url value="/autocompletar.do?criterio=" />'+criterio,
						dataType : "json",
						type : "GET",
						contentType : "application/json;charset=UTF-8",
						success: function(data){
							response(data.slice(0, 5));
						},							

						error : function(jqXHR, textStatus, errorThrown) {
							var errorHtml = "An error ocurred <br/>";
							errorHtml += "Status: " + textStatus + "<br/>";
							errorHtml += "Reason: <pre>" + errorThrown + "</pre> <br/>";
							$("#rtaAutocomplete").html(errorHtml);
						}
					})
				},
				minlength: 1,
				
				select: function( event, ui ){
					
					var usuario = {} 
					usuario = ui.item; 
					$.ajax({
						url : '<c:url value="/invitar.do" />', 
						type : "POST", 
						data:  JSON.stringify(usuario), 
						dataType : "json",
						contentType : "application/json;charset=UTF-8",
						success : function(results, status, xhr){
							dibujarInvitados(results);
						},
						
					});
				},
				
			}).autocomplete( "instance" )._renderItem = function( ul, item ) {
			      return $( "<li>" )
			        .append( "<a><strong>" + item.username + "</strong><br>" + item.apellido +", "+item.nombre+ "</a>" )
			        .appendTo( ul );
			    };
			
			    
			    
	        
			    
			
	  }
	  
	  
	  
	</script>
		  
    
<title><fmt:message key="titulo" /></title>
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a href="/calendar/calendar.do" class="pull-center" ><img src="img/logo.png"></a>
        </div>
        
        <c:if test="${sessionScope.usuario == null}">
        		</div>
    		</nav>    	
        </c:if>
        
		<c:if test="${sessionScope.usuario != null}">
				    <div id="navbar" class="collapse navbar-collapse">
		            <ul class="nav navbar-nav navbar-right">
		            	<li class="li-menu"> 
		            		<a class="navbar-brand"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${sessionScope.usuario.username}</a> 
		            	</li>
		            	<li class="li-menu"><a class="navbar-brand"> | </a></li>
		                <li class="li-menu">
		                	<a class="navbar-brand" href="/calendar/logout.do"><span class="glyphicon glyphicon-off" aria-hidden="true"></span> <fmt:message key="logout" /></a>
		                </li>
		                
		                		                  
		            </ul>
		        </div><!--/.nav-collapse -->
		    </div>
		</nav>
		
		</c:if>

        
        
        


