package interceptors;

import hibernate.model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import dao.UsuarioDao;

public class Interceptor implements HandlerInterceptor{
	
	
	private UsuarioDao usuarioDao;
	
	@Autowired
	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
		
	/*
	 * ex: exception que pueda lanzar el postHandle 
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception ex)
			throws Exception {
		
		//System.out.println("AFTER COMPLETION");
	}
	
	
	/*
	 * mv: podemos setearle cosas a la vista, podemos pedirle scopes seteados. 
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object object, ModelAndView mv) throws Exception {
		
		//System.out.println("POST HANDLE");
		
	}

	
	/*
	 * Object: es la cosa que estamos interceptando, si es un controlador viene la instancia del controlador. new MyController() 
	 */	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		
		Cookie cookie[] = request.getCookies();

		if (cookie != null)
	    {
			for(int i=0; i < cookie.length; i++)
			{
				if(cookie[i].getName().equals("recordar"))
				{
					Usuario usuario = this.usuarioDao.get(cookie[i].getValue().toString());

					if(usuario != null)
					{
						request.getSession().setAttribute("usuario", usuario);
						
						if(request.getRequestURI().contains("login.do"))
						{
							RequestDispatcher rd = request.getRequestDispatcher("/calendar.do");
							rd.forward(request, response);
						}
						
						return true;
					}
					break;
				}
			}
	    }		
		
		
		if(request.getRequestURI().contains("login.do"))
		{
			if(request.getSession().getAttribute("usuario") != null)
			{
				RequestDispatcher rd = request.getRequestDispatcher("/calendar.do");
				rd.forward(request, response);
			}
			return true;
		}
		
		
		
		if(request.getSession().getAttribute("usuario") != null)
		{
			return true;
		}
		else{
			RequestDispatcher rd = request.getRequestDispatcher("/login.do");
			rd.forward(request, response);
			return false;
		}
		
		
	/*	
		
		
		
		
		
		
		
		
		if(request.getRequestURI().contains("login.do"))
		{
			return true;
		}
		
		
		if(request.getSession().getAttribute("usuario") != null)
		{
			return true;
		}
		else{	
			RequestDispatcher rd = request.getRequestDispatcher("/login.do");
			rd.forward(request, response);
			
			return false;
			
		}
		
		-------------------------------------------------------
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/interceptado.jsp");
		rd.forward(request, response); 
		
		
		
		request.setAttribute("mensaje", "Mensaje del interceptor");
		System.out.println("PRE HANDLE");
			
		
		*/
	}
	
	
	
	
	
}
