package es.mdef.gestionpedidos.REST;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RegisterNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(RegisterNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String empleadoNotFoundHandler(RegisterNotFoundException ex) {
		return ex.getMessage();
	}
}
