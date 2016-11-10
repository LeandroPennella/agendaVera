package dao;

import hibernate.model.SalaReunion;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SalaReunionDao {

	private SessionFactory sessionFactory;
	
		
	public SalaReunionDao(){
		
	}
	
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(readOnly = true)	
	public SalaReunion get(int id) {
		Session session = sessionFactory.getCurrentSession(); 
		SalaReunion out = (SalaReunion) session.get(SalaReunion.class, id);
		return out;
	}
	
	@Transactional(readOnly = true)
	public List<SalaReunion> get() {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM SalaReunion AS s ORDER BY s.nombre ASC");
		List<SalaReunion> salas = new ArrayList<SalaReunion>(query.list());
		
		return salas;
	}
	
	
	
}	
	
	
	
	

