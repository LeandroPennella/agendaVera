package controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {

		
		@RequestMapping(value = "/logout")
		public ModelAndView init(HttpServletRequest request, HttpServletResponse response){
			ModelAndView mv = new ModelAndView("/login.do"); 
			this.borrarCookies(request, response);
			request.getSession().setAttribute("usuario", null);
			return mv;
		}
		
		
		private void borrarCookies(HttpServletRequest request, HttpServletResponse response)
		{
			Cookie[] cookies = request.getCookies();
		    if (cookies != null)
		    {
		        for (int i = 0; i < cookies.length; i++) {
		            cookies[i].setValue("");
		            cookies[i].setPath("/");
		            cookies[i].setMaxAge(0);
		            response.addCookie(cookies[i]);
		        }
		    }
		}
		
}
