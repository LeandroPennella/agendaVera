package dao;

import hibernate.model.Usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UsuarioDao {

	private SessionFactory sessionFactory;
	
		
	public UsuarioDao(){
		
	}
	
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
		
	@Transactional(readOnly = true)
	public Usuario login(String username, String password) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Usuario as u where u.username=:user and u.password=:pass");
		query.setString("user", username);
		query.setString("pass", password);
		Usuario resultado = (Usuario) query.uniqueResult(); 
		
		return resultado;
		
	}
	
	@Transactional(readOnly = true)
	public Usuario get(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Usuario as u where u.username=:user");
		query.setString("user", username);
		Usuario resultado = (Usuario) query.uniqueResult(); 
		return resultado;
		
	}
	
	
	
	@Transactional(readOnly = true)
	public Set<Usuario> get(Usuario usuario) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Usuario as u WHERE u <> ? ");
		query.setParameter(0, usuario);
		Set<Usuario> usuarios = new HashSet<Usuario>(query.list());
		
		return usuarios;
	}
	
	@Transactional(readOnly = true)	
	public Usuario get(long id) {
		Session session = sessionFactory.getCurrentSession(); 
		Usuario out = (Usuario) session.get(Usuario.class, id);
		return out;
	}
	
	
	@Transactional(readOnly = true)
	public List<Usuario> like(String criteria, Usuario usuario) {
		Session session = sessionFactory.getCurrentSession();
		Set<Usuario> resultados = new HashSet<Usuario>();
		
		Query query = session.createQuery("from Usuario as u where u <> ? "
				+ "and (u.username like :criteria "
				+ "or u.nombre like :criteria "
				+ "or u.apellido like :criteria)");
		query.setParameter(0, usuario);
		query.setString("criteria", "%"+criteria+"%");
		query.setMaxResults(5);
		resultados.addAll(new HashSet<Usuario>(query.list()));
		
		return new ArrayList<Usuario>(resultados);
	}
	
	
	
}	
	
	
	
	

