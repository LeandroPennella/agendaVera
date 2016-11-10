package views.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Semana {

	List<Date> dias = new ArrayList<Date>();
	
	public Semana(){
		
	}
	
	public Semana(LocalDate diaReferencia){
		
		LocalDate lunes = diaReferencia;
	    while (lunes.getDayOfWeek() != DayOfWeek.MONDAY)
	    {
	    	lunes = lunes.minusDays(1);
	    }
	    	    
	    LocalDate martes = lunes.plusDays(1);;
	    LocalDate miercoles = lunes.plusDays(2);
	    LocalDate jueves = lunes.plusDays(3);
	    LocalDate viernes = lunes.plusDays(4);
	    LocalDate sabado = lunes.plusDays(5);
	    LocalDate domingo = lunes.plusDays(6);

	    dias.add(Date.from(lunes.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	    dias.add(Date.from(martes.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	    dias.add(Date.from(miercoles.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	    dias.add(Date.from(jueves.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	    dias.add(Date.from(viernes.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	    dias.add(Date.from(sabado.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	    dias.add(Date.from(domingo.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	    	    
	    
	}
		
	
	
	public List<Date> getDias() {
		return dias;
	}

	public void setDias(List<Date> dias) {
		this.dias = dias;
	}

	




	
	
	
	
}
