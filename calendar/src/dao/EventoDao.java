package dao;

import hibernate.model.Evento;
import hibernate.model.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EventoDao {

	private SessionFactory sessionFactory;

	public EventoDao() {

	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(readOnly = true)	
	public Evento get(long id) {
		Session session = sessionFactory.getCurrentSession(); 
		Evento out = (Evento) session.get(Evento.class, id);
		return out;
	}
	
	@Transactional(readOnly = true)
	public Set<Evento> getEventosDelDia(Usuario usuario, Date dia) {
		
		Session session = sessionFactory.getCurrentSession();
		
		String consulta = "FROM Evento as e WHERE e.owner = ? AND e.fecha = ?";
		Query query = session.createQuery(consulta);
		query.setParameter(0, usuario);
		query.setParameter(1, dia);
		query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		List<Evento> eventosOwner = (ArrayList<Evento>) query.list();
		
		consulta = "FROM Reunion as r INNER JOIN FETCH r.invitados as i WHERE i.usuario = ? and r.fecha = ?";
		query = session.createQuery(consulta);
		query.setParameter(0, usuario);
		query.setParameter(1, dia);
		query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		List<Evento> reunionInvitado = (ArrayList<Evento>) query.list();
		
		Set<Evento> misEventos = new HashSet<Evento>();
		misEventos.addAll(eventosOwner);
		misEventos.addAll(reunionInvitado);
		
		
		
//		if(misEventos.size() != 0)
//		{
//			System.out.println("");
//			System.out.println("");
//			System.out.println("----------  QUERYYYYY ---------------");
//			
//			for (Evento evento : misEventos) {
//				System.out.println(evento);
//			}
//			
//			System.out.println("");
//			System.out.println("");
//		}
		
		//Collections.sort(misEventos, new EventoComparator());

		return misEventos;
		
	}

}
