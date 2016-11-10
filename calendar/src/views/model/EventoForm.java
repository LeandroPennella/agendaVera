package views.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class EventoForm {
	
	private Long idEventoPrivado = null;
	private String nombre;
	private String descripcion;
	private String direccion;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fecha;
	private String inicio;
	private String fin;
	private Boolean soyModificacion = false;
	
	
	public EventoForm(){
		
	}


	public Long getIdEventoPrivado() {
		return idEventoPrivado;
	}


	public void setIdEventoPrivado(Long idEventoPrivado) {
		this.idEventoPrivado = idEventoPrivado;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public String getInicio() {
		return inicio;
	}


	public void setInicio(String inicio) {
		this.inicio = inicio;
	}


	public String getFin() {
		return fin;
	}


	public void setFin(String fin) {
		this.fin = fin;
	}


	public Boolean getSoyModificacion() {
		return soyModificacion;
	}


	public void setSoyModificacion(Boolean soyModificacion) {
		this.soyModificacion = soyModificacion;
	}




	
	
}
