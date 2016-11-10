package views.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarGrilla {
	
	List<EventoDia> listaEventos;
	LocalDate diaReferencia;
	
	
	public CalendarGrilla(){
		this.listaEventos = new ArrayList<EventoDia>();
	}

	public List<EventoDia> getListaEventos() {
		return listaEventos;
	}


	public void setListaEventos(List<EventoDia> listaEventos) {
		this.listaEventos = listaEventos;
	}

	public LocalDate getDiaReferencia() {
		return diaReferencia;
	}

	public void setDiaReferencia(LocalDate diaReferencia) {
		this.diaReferencia = diaReferencia;
	}

	
	
	
	
	
	
}
