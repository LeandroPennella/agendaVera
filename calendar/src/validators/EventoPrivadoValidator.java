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

import views.model.EventoForm;

@Component
public class EventoPrivadoValidator implements Validator{
	
		
	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object object, Errors errors) {
		
		EventoForm evento = (EventoForm) object;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "evento.error.nombre.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "direccion", "eventoprivado.error.direccion.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "inicio", "evento.error.hora.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fin", "evento.error.hora.empty");
		
		if(evento.getFecha() != null)
		{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fecha", "evento.error.fecha.empty");
		}
		
		
		DateFormat formatter = new SimpleDateFormat("hh:mm");
		Date inicio = null;
		Date fin = null;
		
		try {
			inicio = formatter.parse(evento.getInicio());
			fin = formatter.parse(evento.getFin());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		if(inicio.equals(null) || fin.equals(null) || inicio.compareTo(fin) >= 0)
		{
			errors.rejectValue("fin", "evento.error.hora.rango");
		}
		
		
//		if(evento.getInicio().compareTo(evento.getFin()) >= 0)
//		{
//			errors.rejectValue("fin", "reunion.error.hora.rango");
//		}
//		
//		
		
		
	}
	
	
	
}
