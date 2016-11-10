package controllers;

import hibernate.model.Usuario;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import validators.UsuarioValidator;
import views.model.LoginForm;
import dao.UsuarioDao;


@Controller
@SessionAttributes({"usuario"})
public class LoginController {
	
	private UsuarioDao usuarioDao;
	private UsuarioValidator usuarioValidator;
		
	@Autowired
	public void setUsuarioValidator(UsuarioValidator usuarioValidator) {
		this.usuarioValidator = usuarioValidator;
	}
		
	
	@Autowired
	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	@Autowired
	private SessionLocaleResolver localeResolver;
	
		
	
	@RequestMapping(value = "/login")
	public ModelAndView init(ModelMap model){
		
		ModelAndView mv = new ModelAndView("/jsp/login.jsp");
		mv.addObject("login", new LoginForm());
		
		return mv;
	}
		
	
		
	@RequestMapping(value = "/validalogin")
	public ModelAndView validate(@ModelAttribute("login") LoginForm loginForm, BindingResult result, SessionStatus status, 
			HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mv = new ModelAndView("/jsp/login.jsp");
		UsuarioDao usuarioDao = this.usuarioDao;
		
		this.usuarioValidator.validate(loginForm, result);
		
		if (result.hasErrors()) {
			return mv;
		}
		
		Usuario usuario = usuarioDao.login(loginForm.getUsername(), loginForm.getPassword());
				
		if(loginForm.getRecordar())
		{
			this.recordarUsuario(usuario, response);
		}
				
		if(usuario == null)
		{
			mv.addObject("existeusuario", false);
			return mv;
		}
		
		mv = new ModelAndView("/calendar.do");
		mv.addObject("usuario", usuario);
		
		Config.set(request.getSession(), Config.FMT_LOCALE, new java.util.Locale(usuario.getIdioma()));
		localeResolver.setLocale(request, response, new java.util.Locale(usuario.getIdioma()));		
		
		return mv;
	}
	
	
	
	private void recordarUsuario(Usuario usuario, HttpServletResponse response)
	{
		Cookie co = new Cookie("recordar", usuario.getUsername());
		int diezAnios = 60 * 60 * 24 * 365 * 10;
		co.setMaxAge(diezAnios);
		co.setPath("/");
		response.addCookie(co);
	}
	
	
	
}
