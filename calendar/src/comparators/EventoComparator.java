package comparators;

import hibernate.model.Evento;

import java.util.Comparator;

public class EventoComparator implements Comparator<Evento>{

	@Override
	public int compare(Evento e1, Evento e2) {
		
		return e1.getInicio().compareTo(e2.getInicio());
		
	}
	
	
}
