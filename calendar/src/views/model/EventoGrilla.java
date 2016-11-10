package views.model;

public class EventoGrilla {
	
	private Long id;
	private String nombre;
	private String inicio;
	private String fin;
	private Integer estadoInvitacion;
	private Boolean soyReunion = false;
	private Boolean soyOwner = false;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public Integer getEstadoInvitacion() {
		return estadoInvitacion;
	}
	public void setEstadoInvitacion(Integer estadoInvitacion) {
		this.estadoInvitacion = estadoInvitacion;
	}
	public Boolean getSoyReunion() {
		return soyReunion;
	}
	public void setSoyReunion(Boolean soyReunion) {
		this.soyReunion = soyReunion;
	}
	@Override
	public String toString() {
		return "EventoGrilla [id=" + id + ", nombre=" + nombre + ", inicio="
				+ inicio + ", fin=" + fin + ", estadoInvitacion="
				+ estadoInvitacion + ", soyReunion=" + soyReunion + "]";
	}
	public Boolean getSoyOwner() {
		return soyOwner;
	}
	public void setSoyOwner(Boolean soyOwner) {
		this.soyOwner = soyOwner;
	}
	
	
	
	
	
}
