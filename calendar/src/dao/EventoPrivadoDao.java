package dao;

import hibernate.model.EventoPrivado;
import hibernate.model.Reunion;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EventoPrivadoDao {

	private SessionFactory sessionFactory;
			
	public EventoPrivadoDao(){
		
	}
	
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(readOnly = true)
	public EventoPrivado get(long id) {
		Session session = sessionFactory.getCurrentSession(); 
		EventoPrivado out = (EventoPrivado) session.get(EventoPrivado.class, id);
		return out;
	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(EventoPrivado eventoPrivado) {
		Session session = sessionFactory.getCurrentSession();		
		session.saveOrUpdate(eventoPrivado);
	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(EventoPrivado eventoPrivado) {
		Session session = sessionFactory.getCurrentSession();		
		session.delete(eventoPrivado);
	}
	
	
	
	
	
	
	
	
	
	
		
	
	
	
	
	
}	
	
	
	
	

