package validators;

import hibernate.model.Usuario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import views.model.ReunionForm;

@Component
public class ReunionValidator implements Validator{
	
		
	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object object, Errors errors) {
		ReunionForm reunion = (ReunionForm) object;
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "evento.error.nombre.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "inicio", "evento.error.hora.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fin", "evento.error.hora.empty");
				
		if(reunion.getInvitados().size()  == 0)
		{
			errors.rejectValue("invitados", "reunion.error.invitados.empty");
		}
		
		if(reunion.getSalas().size() == 0)
		{
			errors.rejectValue("idSala", "reunion.error.sala.empty");
		}
		
		
		DateFormat formatter = new SimpleDateFormat("hh:mm");
		Date inicio = null;
		Date fin = null;
		
		try {
			inicio = formatter.parse(reunion.getInicio());
			fin = formatter.parse(reunion.getFin());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(inicio.equals(null) || fin.equals(null) || inicio.compareTo(fin) >= 0)
		{
			errors.rejectValue("fin", "evento.error.hora.rango");
		}
				
		
		if(reunion.getFecha() != null)
		{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fecha", "evento.error.fecha.empty");	
		}
		
	}
	
	
	
}
