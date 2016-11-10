package controllers;

import hibernate.model.Reunion;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import dao.ReunionDao;



@Controller
@SessionAttributes({"usuario"})
public class VisualizarController {

	private ReunionDao reunionDao;
	
	@Autowired
	public void setReunionDao(ReunionDao reunionDao) {
		this.reunionDao = reunionDao;
	}
	
	
	
	@RequestMapping(value = "/visualizar")
	public ModelAndView ver(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("/jsp/visualizar.jsp");
		String idReunion = request.getParameter("id");
		
		if(idReunion == null)
		{
			return new ModelAndView("/calendar.do");
		}
		
		Reunion reunion = this.reunionDao.get(Long.parseLong(idReunion));
		
		mv.addObject("reunion", reunion);
		
		return mv;
	}	
	
}
