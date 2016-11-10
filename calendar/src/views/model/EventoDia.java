package views.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventoDia {
	
	private Date dia;
	private List<EventoGrilla> eventos;
	
	
	public EventoDia(){
		this.eventos = new ArrayList<EventoGrilla>();
	}


	public Date getDia() {
		return dia;
	}


	public void setDia(Date dia) {
		this.dia = dia;
	}


	public List<EventoGrilla> getEventos() {
		return eventos;
	}


	public void setEventos(List<EventoGrilla> eventos) {
		this.eventos = eventos;
	}
	
	
	
	
	

}
