package controllers;

import hibernate.model.Invitado;
import hibernate.model.Reunion;
import hibernate.model.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import validators.ReunionValidator;
import views.model.ReunionForm;
import dao.ReunionDao;
import dao.SalaReunionDao;
import dao.UsuarioDao;

@Controller
@SessionAttributes({ "usuario", "reunion" })
public class ReunionController {

	private SalaReunionDao salaDao;
	private UsuarioDao usuarioDao;
	private ReunionDao reunionDao;
	private ReunionValidator reunionValidator;
		

	@Autowired
	public void setSalaReunionDao(SalaReunionDao salaDao) {
		this.salaDao = salaDao;
	}

	@Autowired
	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@Autowired
	public void setReunionValidator(ReunionValidator reunionValidator) {
		this.reunionValidator = reunionValidator;
	}

	@Autowired
	public void setReunionDao(ReunionDao reunionDao) {
		this.reunionDao = reunionDao;
	}

	
	
	@RequestMapping(value = "/reunion")
	public ModelAndView init(@RequestParam(value = "idReunion", required = false) String paramIdReunion, @ModelAttribute("usuario") Usuario usuario) {
		
		ModelAndView mv = new ModelAndView("/jsp/reunion.jsp");
		ReunionForm reunionForm = new ReunionForm();
		reunionForm.setSalas(salaDao.get());
		
		
		if(paramIdReunion != null)
		{
			Long idReunion = Long.parseLong(paramIdReunion);
			Reunion reunion = this.reunionDao.get(idReunion);
		
			reunionForm.setSoyModificacion(true);
			reunionForm.setIdReunion(idReunion);
			reunionForm.setNombre(reunion.getNombre());
			reunionForm.setTemario(reunion.getTemario());
			reunionForm.setFecha(reunion.getFecha());
			reunionForm.setInicio(reunion.getInicio());
			reunionForm.setFin(reunion.getFin());
			reunionForm.setInvitadosAnteriores(reunion.getInvitados());
			reunionForm.setInvitados(reunion.getInvitados());
			reunionForm.setIdSala(reunion.getSala().getId());
		}

		mv.addObject("reunion", reunionForm);
		mv.addObject("horas", this.getListaHoras());
		

		return mv;
	}
	
	
	
	@RequestMapping(value = "/borrarreunion")
	public ModelAndView borrar(@ModelAttribute("reunion") ReunionForm reunion) {
		
		this.reunionDao.delete(this.reunionDao.get(reunion.getIdReunion()));
		return new ModelAndView("/calendar.do");
	}
	
	
	
	@RequestMapping(value = "/validareunion")
	public ModelAndView validate(@ModelAttribute("usuario") Usuario usuario,
			@ModelAttribute("reunion") ReunionForm reunionVista,
			BindingResult result, SessionStatus status) {
		
		this.reunionValidator.validate(reunionVista, result);
		
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("/jsp/reunion.jsp");
			mv.addObject("horas", this.getListaHoras());
			return mv;
		}	

		Reunion reunion;
		
		if (reunionVista.getSoyModificacion()) {
			reunion = this.reunionDao.get(reunionVista.getIdReunion());
		} else {
			reunion = new Reunion();
		}
		
		
		reunion.setNombre(reunionVista.getNombre());
		reunion.setTemario(reunionVista.getTemario());
		reunion.setFecha(reunionVista.getFecha());
		reunion.setInicio(reunionVista.getInicio());
		reunion.setFin(reunionVista.getFin());
		//reunion.setInvitados(reunionVista.getInvitados());
		this.actualizarInvitaciones(reunion, reunionVista);
		reunion.setSala(salaDao.get(reunionVista.getIdSala()));
		reunion.setOwner(usuario);
		reunionDao.save(reunion);
		
		//mv.addObject("reunion", reunionVista);
		
		return new ModelAndView("/calendar.do");
	}

		
	
	
	
	
	private void actualizarInvitaciones(Reunion reunion, ReunionForm reunionForm)
	{
		reunion.getInvitados().clear();
		
		for (Invitado inv : reunionForm.getInvitados()) {
			reunion.getInvitados().add(inv);
		}
				
		for (Invitado in : reunion.getInvitados()) {
			System.out.println("INVITADO: "+in.getUsuario().getUsername());
		}

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
		
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	
	
	
	
	@RequestMapping(value = "/invitar", method = RequestMethod.POST)
	public  @ResponseBody Set<Invitado> invitar(@ModelAttribute("reunion") ReunionForm reunionForm, @RequestBody Usuario usuario) {
		
		Boolean existe = false;	
		for(Invitado iAnterior : reunionForm.getInvitadosAnteriores())
		{
						
			if(iAnterior.getUsuario().getId() == usuario.getId())
			{
				System.out.println("ANTERIOR: "+iAnterior.getUsuario());
				System.out.println("EXISTE: "+usuario);
				reunionForm.getInvitados().add(iAnterior);
				existe = true;
			}
		}
		
		if(!existe) // Si el usuario no existia en los invitados lo agrega como A CONFIRMAR
		{
			System.out.println("AGREGADO: "+usuario);
			Invitado invitado = new Invitado();
			invitado.setUsuario(usuario);
			invitado.setEstado(1);
			reunionForm.getInvitados().add(invitado);
		}
		
		return reunionForm.getInvitados();
		
	}
		
	@RequestMapping(value = "/quitarinvitado", method = RequestMethod.POST)
	public  @ResponseBody Set<Invitado> quitar(@ModelAttribute("reunion") ReunionForm reunionForm, @RequestBody Usuario borrarUsuario) {
			
			borrarUsuario = this.usuarioDao.get(borrarUsuario.getId());
		
			for (Invitado inv : reunionForm.getInvitados()) {
				
//				System.out.println("ACTUAL: "+inv.getUsuario());
//				System.out.println("BORRAR: "+borrarUsuario);
				
				if(borrarUsuario.equals(inv.getUsuario()))
				{
					reunionForm.getInvitados().remove(inv);
					break;
				}
			}
		
		return reunionForm.getInvitados();
	}
	
	
	
	@RequestMapping(value = "/autocompletar")
	public @ResponseBody List<Usuario> autocomplete(@RequestParam("criterio") String criterio, @ModelAttribute Usuario usuario) {
				
		return this.usuarioDao.like(criterio, usuario);
	}
	
	
}
