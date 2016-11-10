package dao;

import hibernate.model.Reunion;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ReunionDao {

	private SessionFactory sessionFactory;
	
		
	public ReunionDao(){
		
	}
	
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	@Transactional(readOnly = true)	
	public Reunion get(long id) {
		Session session = sessionFactory.getCurrentSession(); 
		Reunion out = (Reunion) session.get(Reunion.class, id);
		return out;
	}
	
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(Reunion reunion) {
		Session session = sessionFactory.getCurrentSession();		
		session.saveOrUpdate(reunion);
	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Reunion reunion) {
		Session session = sessionFactory.getCurrentSession();		
		session.delete(reunion);
	}
	
}	
	
	
	
	

