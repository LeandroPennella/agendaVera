package views.model;

import hibernate.model.Invitado;
import hibernate.model.SalaReunion;
import hibernate.model.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

public class ReunionForm {
	
	private String nombre;
	private String temario;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fecha;
	private String inicio;
	private String fin;
	private List <SalaReunion> salas;
	private int idSala;
	private Set <Invitado> invitadosAnteriores;
	private Set <Invitado> invitados;
	private List<Long> idInvitados;
	private Boolean soyModificacion = false;
	private Long idReunion = null;
	
	
	public ReunionForm(){
		this.salas = new ArrayList<SalaReunion>();
		this.invitadosAnteriores = new HashSet<Invitado>();
		this.invitados = new HashSet<Invitado>();
		this.idInvitados = new ArrayList<Long>();
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTemario() {
		return temario;
	}


	public void setTemario(String temario) {
		this.temario = temario;
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


	public List<SalaReunion> getSalas() {
		return salas;
	}


	public void setSalas(List<SalaReunion> salas) {
		this.salas = salas;
	}


	public int getIdSala() {
		return idSala;
	}


	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}


	public Set<Invitado> getInvitadosAnteriores() {
		return invitadosAnteriores;
	}


	public void setInvitadosAnteriores(Set<Invitado> invitadosAnteriores) {
		this.invitadosAnteriores = invitadosAnteriores;
	}


	


	public Set<Invitado> getInvitados() {
		return invitados;
	}


	public void setInvitados(Set<Invitado> invitados) {
		this.invitados = invitados;
	}


	public List<Long> getIdInvitados() {
		return idInvitados;
	}


	public void setIdInvitados(List<Long> idInvitados) {
		
		for (Long long1 : idInvitados) {
			System.out.println("ID: "+long1);
		}
		
		this.idInvitados = idInvitados;
	}


	public Boolean getSoyModificacion() {
		return soyModificacion;
	}


	public void setSoyModificacion(Boolean soyModificacion) {
		this.soyModificacion = soyModificacion;
	}


	public Long getIdReunion() {
		return idReunion;
	}


	public void setIdReunion(Long idReunion) {
		this.idReunion = idReunion;
	}

	
	
	


	



	
	

	
	
}
