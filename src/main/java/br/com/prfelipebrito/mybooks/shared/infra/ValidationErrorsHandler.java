package br.com.prfelipebrito.mybooks.shared.infra;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ValidationErrorsHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ValidationError> handle(MethodArgumentNotValidException exception) {
		var errors = new ArrayList<ValidationError>();

		var fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			var message = messageSource.getMessage(e, LocaleContextHolder.getLocale());

			errors.add(new ValidationError(e.getField(), message));
		});

		return errors;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public List<ValidationError> handle(MethodArgumentTypeMismatchException exception) {
		var errors = new ArrayList<ValidationError>();

		errors.add(new ValidationError(exception.getName(), exception.getMessage()));

		return errors;
	}
}
