package validators;

import hibernate.model.Usuario;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import views.model.LoginForm;

@Component
public class UsuarioValidator implements Validator{
	
		
	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		LoginForm u = (LoginForm) object;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "login.error.username.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "login.error.password.empty");
		
	}
	
	
	
}
