package hibernate.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;




public abstract class Evento implements Comparable<Evento> {
	
	private long id;
	private String nombre;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fecha;
	private String inicio;
	private String fin;
	private Usuario owner;
	
	
	public Evento(){
		
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
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


	public Usuario getOwner() {
		return owner;
	}


	public void setOwner(Usuario owner) {
		this.owner = owner;
	}

	public abstract String queSoy();
	


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((fin == null) ? 0 : fin.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((inicio == null) ? 0 : inicio.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (fin == null) {
			if (other.fin != null)
				return false;
		} else if (!fin.equals(other.fin))
			return false;
		if (id != other.id)
			return false;
		if (inicio == null) {
			if (other.inicio != null)
				return false;
		} else if (!inicio.equals(other.inicio))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}


	public int compareTo(Evento e){
		DateFormat formatter = new SimpleDateFormat("hh:mm");
		Date inicio1=null;
		Date inicio2=null;
		
		try {
			inicio1= formatter.parse(this.getInicio());
			inicio2= formatter.parse(e.getInicio());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
				
		return inicio1.compareTo(inicio2);
	}
		
	
	
}
