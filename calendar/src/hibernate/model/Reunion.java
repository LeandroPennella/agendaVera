package hibernate.model;

import java.util.HashSet;
import java.util.Set;

public class Reunion extends Evento{
	
	private long id;
	private SalaReunion sala;
	private Set<Invitado> invitados;
	private String temario;
	
	
	public Reunion(){
		this.invitados = new HashSet<Invitado>();
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public SalaReunion getSala() {
		return sala;
	}
	public void setSala(SalaReunion sala) {
		this.sala = sala;
	}
	public Set<Invitado> getInvitados() {
		return invitados;
	}
	public void setInvitados(Set<Invitado> invitados) {
		this.invitados = invitados;
	}
	public String getTemario() {
		return temario;
	}
	public void setTemario(String temario) {
		this.temario = temario;
	}
	
	
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((invitados == null) ? 0 : invitados.hashCode());
		result = prime * result + ((sala == null) ? 0 : sala.hashCode());
		result = prime * result + ((temario == null) ? 0 : temario.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reunion other = (Reunion) obj;
		if (id != other.id)
			return false;
		if (invitados == null) {
			if (other.invitados != null)
				return false;
		} else if (!invitados.equals(other.invitados))
			return false;
		if (sala == null) {
			if (other.sala != null)
				return false;
		} else if (!sala.equals(other.sala))
			return false;
		if (temario == null) {
			if (other.temario != null)
				return false;
		} else if (!temario.equals(other.temario))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Reunion [id=" + id + ", temario=" + temario + "]";
	}
	
	
	@Override
	public String queSoy() {
		
		return "reunion";
	}
	
	
}
