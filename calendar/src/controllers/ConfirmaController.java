package controllers;

import hibernate.model.Invitado;
import hibernate.model.Reunion;
import hibernate.model.Usuario;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import dao.ReunionDao;



@Controller
@SessionAttributes({"usuario", "reunion"})
public class ConfirmaController {
	
	private ReunionDao reunionDao;
	
	@Autowired
	public void setReunionDao(ReunionDao reunionDao) {
		this.reunionDao = reunionDao;
	}	
	
	@RequestMapping(value = "/confirmar")
	public ModelAndView init(HttpServletRequest request){
		
		ModelAndView mv;
		String idReunion = request.getParameter("id");
		
		if(idReunion != null)
		{
			mv = new ModelAndView("/jsp/confirmar.jsp");
			Reunion reunion = this.reunionDao.get(Long.parseLong(idReunion));
			System.out.println(reunion.getNombre());
			mv.addObject("reunion", reunion);
		}
		else
		{
			mv = new ModelAndView("/calendar.do");
		}
		
		return mv;
	}
	
	
	
	@RequestMapping(value = "/aceptar")
	public ModelAndView aceptar(@ModelAttribute("reunion") Reunion reunion, @ModelAttribute("usuario") Usuario usuario){
		ModelAndView mv = new ModelAndView("/calendar.do");
		confirmarReunion(usuario, reunion, 2);
		
		return mv;
	}
	
	
	@RequestMapping(value = "/rechazar")
	public ModelAndView rechazar(@ModelAttribute("reunion") Reunion reunion, @ModelAttribute("usuario") Usuario usuario){
		ModelAndView mv = new ModelAndView("/calendar.do");
		confirmarReunion(usuario, reunion, 3);
		
		return mv;
	}
		
		
	
	private void confirmarReunion(Usuario usuario, Reunion reunion, int estadoConfirmacion)
	{		
		for (Invitado invitado : reunion.getInvitados()) {
			
			if(invitado.getUsuario().equals(usuario))
			{
				invitado.setEstado(estadoConfirmacion);
				this.reunionDao.save(reunion);
			}
		}
	}
	
	
	
}
