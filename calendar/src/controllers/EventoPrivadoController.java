package controllers;

import hibernate.model.EventoPrivado;
import hibernate.model.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import validators.EventoPrivadoValidator;
import views.model.EventoForm;
import dao.EventoPrivadoDao;


@Controller
@SessionAttributes({"usuario", "evento"})
public class EventoPrivadoController {
		
	private EventoPrivadoDao eventoPrivadoDao;
	private EventoPrivadoValidator eventoPrivadoValidator;
		
	@Autowired
	public void setEventoPrivadoValidator(EventoPrivadoValidator eventoPrivadoValidator) {
		this.eventoPrivadoValidator = eventoPrivadoValidator;
	}
		
	
	@Autowired
	public void setEventoPrivadoDao(EventoPrivadoDao eventoPrivadoDao) {
		this.eventoPrivadoDao = eventoPrivadoDao;
	}
	
	
	@RequestMapping(value = "/eventoprivado")
	public ModelAndView init(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView("/jsp/eventoprivado.jsp");
		
		mv.addObject("horas", this.getListaHoras());
		
		EventoForm eventoForm = new EventoForm();
		
		
		if(request.getParameter("id")!=null)
		{
			Long idEvento = Long.parseLong(request.getParameter("id"));
			EventoPrivado evento = this.eventoPrivadoDao.get(idEvento);
			
			eventoForm.setIdEventoPrivado(idEvento);
			eventoForm.setNombre(evento.getNombre());
			eventoForm.setDescripcion(evento.getDescripcion());
			eventoForm.setDireccion(evento.getDireccion());
			eventoForm.setFecha(evento.getFecha());
			eventoForm.setInicio(evento.getInicio());
			eventoForm.setFin(evento.getFin());
			eventoForm.setSoyModificacion(true);
		}
		
		mv.addObject("evento", eventoForm);
		return mv;
	}
	
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, false));
	}
	
	
	@RequestMapping(value = "/validaeventoprivado")
	public ModelAndView validate(@ModelAttribute("usuario") Usuario usuario, 
			@ModelAttribute("evento") EventoForm eventoForm, 
			BindingResult result, SessionStatus status){
		
		
		this.eventoPrivadoValidator.validate(eventoForm, result);
		
		
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("/jsp/eventoprivado.jsp");
			mv.addObject("horas", this.getListaHoras());
			return mv;
		}
		
		EventoPrivado evento;
		
		if(eventoForm.getSoyModificacion())
		{
			evento = this.eventoPrivadoDao.get(eventoForm.getIdEventoPrivado());
		}
		else{
			evento = new EventoPrivado();
		}
		
		evento.setNombre(eventoForm.getNombre());
		evento.setDescripcion(eventoForm.getDescripcion());
		evento.setDireccion(eventoForm.getDireccion());
		evento.setFecha(eventoForm.getFecha());
		evento.setInicio(eventoForm.getInicio());
		evento.setFin(eventoForm.getFin());
		evento.setOwner(usuario);
		
		this.eventoPrivadoDao.save(evento);
				
		return new ModelAndView("/calendar.do");
	}
	
	
	@RequestMapping(value = "/borrarevento")
	public ModelAndView borrar(@ModelAttribute("evento") EventoForm evento) {
		 
		this.eventoPrivadoDao.delete(this.eventoPrivadoDao.get(evento.getIdEventoPrivado()));
		return new ModelAndView("/calendar.do");
	}
	
	
	
	private List<String> getListaHoras()
	{
		List<String> horas = new ArrayList<String>();
		
		for(int i=0; i<=23; i++)
		{
			horas.add(i+":00");
			horas.add(i+":30");
		}
		
		return horas;
	}
	
	
	
}
