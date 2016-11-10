package controllers;

import hibernate.model.Evento;
import hibernate.model.EventoPrivado;
import hibernate.model.Invitado;
import hibernate.model.Reunion;
import hibernate.model.Usuario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import views.model.CalendarGrilla;
import views.model.EventoDia;
import views.model.EventoGrilla;
import views.model.Semana;
import dao.EventoDao;
import dao.EventoPrivadoDao;
import dao.ReunionDao;


@Controller
@SessionAttributes({"usuario", "grilla"})
public class CalendarController {
	
	private EventoDao eventoDao;
	private ReunionDao reunionDao;
	private EventoPrivadoDao eventoPrivadoDao;
	
	@Autowired
	public void setEventoDao(EventoDao eventoDao) {
		this.eventoDao = eventoDao;
	}	
	
	@Autowired
	public void setReunionDao(ReunionDao reunionDao) {
		this.reunionDao = reunionDao;
	}
	
	@Autowired
	public void setEventoPrivadoDao(EventoPrivadoDao eventoPrivadoDao) {
		this.eventoPrivadoDao = eventoPrivadoDao;
	}
	
	@RequestMapping(value = "/calendar")
	public ModelAndView init(@ModelAttribute("usuario") Usuario usuario) throws ParseException{
		ModelAndView mv = new ModelAndView("/jsp/calendar.jsp"); 
		LocalDate diaReferencia = LocalDate.now();
		Semana semana = new Semana(diaReferencia);
		CalendarGrilla grilla = new CalendarGrilla();
		grilla.setDiaReferencia(diaReferencia);
		for (Date dia : semana.getDias()) {
			EventoDia evento = new EventoDia();
			evento.setDia(dia);
			evento.setEventos(this.convertirEnEventoGrilla(this.eventoDao.getEventosDelDia(usuario, dia), usuario));
			grilla.getListaEventos().add(evento);
		}
		
		mv.addObject("grilla", grilla);
		mv.addObject("horario", getListaHoras());
		mostrarGrilla(grilla);
		
		return mv;
		
	}
	
	

	private List<String> getListaHoras()
	{
		List<String> horas = new ArrayList<String>();
		for(int i=0; i<=23; i++){
			horas.add(i+":00");
			horas.add(i+":30");
		}
		return horas;
	}
	
	
	
	public void mostrarGrilla(CalendarGrilla grilla)
	{
		for (EventoDia eventoDia : grilla.getListaEventos()) {
			
			System.out.println("------ "+eventoDia.getDia()+ " -------");
			for (EventoGrilla eventos : eventoDia.getEventos()) {
				
				System.out.println("  - ID: "+eventos.getId());
				System.out.println("  - Nombre: "+eventos.getNombre());
				System.out.println("  - Estado: "+eventos.getEstadoInvitacion());
				System.out.println("  - Incio: "+eventos.getInicio());
				System.out.println("  - Fin: "+eventos.getFin());
				System.out.println("  - soyOwner: "+eventos.getSoyOwner());
				System.out.println("  - soyReunion: "+eventos.getSoyReunion());
			}
			System.out.println("");
		} 
	}
	
	
	
	
	
	private List<EventoGrilla> convertirEnEventoGrilla(Set<Evento> eventosModelo, Usuario usuario)
	{
		List<EventoGrilla> eventosGrilla = new ArrayList<EventoGrilla>(); 
		
		for (Evento e : eventosModelo) {
			EventoGrilla evento = new EventoGrilla();
			evento.setId(e.getId());
			evento.setNombre(e.getNombre());
			evento.setInicio(e.getInicio());
			evento.setFin(e.getFin());

			if(e.getOwner().getUsername().equals(usuario.getUsername()))
			{
				evento.setSoyOwner(true);
			}
			
			if(e.queSoy().equals("reunion"))
			{
				Reunion reunionTemporal = (Reunion) e;
				evento.setSoyReunion(true);
				
				for (Invitado inv :  reunionTemporal.getInvitados()) {
					
					System.out.println(inv.getUsuario());
					System.out.println(usuario);
					if(inv.getUsuario().getId() == usuario.getId())
					{
						System.out.println("ESTADO: "+inv.getEstado());
						evento.setEstadoInvitacion(inv.getEstado());
					}
				}
			}
			
			eventosGrilla.add(evento);
		}
		
		return eventosGrilla;
	}
	
	
	@RequestMapping(value = "/anterior")
	public ModelAndView anterior(@ModelAttribute("usuario") Usuario usuario, 
			@ModelAttribute("grilla") CalendarGrilla grilla) throws ParseException{
		
		ModelAndView mv = new ModelAndView("/jsp/calendar.jsp"); 
		LocalDate diaReferencia = grilla.getDiaReferencia().minusWeeks(1);
		grilla = new CalendarGrilla();
		grilla.setDiaReferencia(diaReferencia);
		Semana semana = new Semana(diaReferencia);
		
		for (Date dia : semana.getDias()) {
			EventoDia evento = new EventoDia();
			evento.setDia(dia);
			evento.setEventos(this.convertirEnEventoGrilla(this.eventoDao.getEventosDelDia(usuario, dia), usuario));
			grilla.getListaEventos().add(evento);
		}
		mv.addObject("grilla", grilla);
		mv.addObject("horario", getListaHoras());
		
		return mv;
		
	}
	
	
	@RequestMapping(value = "/siguiente")
	public ModelAndView siguiente(@ModelAttribute("usuario") Usuario usuario, @ModelAttribute("grilla") CalendarGrilla grilla) throws ParseException{
		ModelAndView mv = new ModelAndView("/jsp/calendar.jsp"); 
		
		LocalDate diaReferencia = grilla.getDiaReferencia().plusWeeks(1);
		grilla = new CalendarGrilla();
		grilla.setDiaReferencia(diaReferencia);
		Semana semana = new Semana(diaReferencia);
		
		for (Date dia : semana.getDias()) {
			EventoDia evento = new EventoDia();
			evento.setDia(dia);
			evento.setEventos(this.convertirEnEventoGrilla(this.eventoDao.getEventosDelDia(usuario, dia), usuario));
			grilla.getListaEventos().add(evento);
		}
		mv.addObject("grilla", grilla);
		mv.addObject("horario", getListaHoras());
		
		return mv;
		
	}
	
	
	@RequestMapping(value = "/updatehorario", method = RequestMethod.POST)
	public @ResponseBody void actualizarHorario(@ModelAttribute("grilla") CalendarGrilla grilla, @RequestBody EventoGrilla evento) {
			
			Evento e = this.eventoDao.get(evento.getId());
			e.setInicio(evento.getInicio());
			e.setFin(evento.getFin());
			
			switch(e.queSoy())
			{
				case "reunion": this.reunionDao.save((Reunion)e); break;
				case "eventoprivado": this.eventoPrivadoDao.save((EventoPrivado)e); break; 
			}
			
	}
	
	
	
}
