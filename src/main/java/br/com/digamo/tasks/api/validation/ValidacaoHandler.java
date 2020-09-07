package br.com.digamo.tasks.api.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ValidacaoHandler {

	@Autowired
	private MessageSource messageSource; 
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<Error> handleValidationException( MethodArgumentNotValidException methodArgumentNotValidException ) {
		
		List<Error> dtoErrors = new ArrayList<>();
		List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach(e -> {
			String msg = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			Error error = new Error(e.getField(), msg); 
			
			dtoErrors.add(error);
		});
		
		return dtoErrors;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ResponseStatusException.class)
	public List<Error> handleResponseStatusException( ResponseStatusException responseStatusException ) {
		
		List<Error> dtoErrors = new ArrayList<>();
		
		dtoErrors.add(new Error("", responseStatusException.getReason()));
		
		return dtoErrors;
	}
	
}


/*
@RestControllerAdvice
public class ValidacaoHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error> errors = getErrors(ex);
        ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
        return new ResponseEntity<>(errorResponse, status);
    }

    private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status, List<Error> errors) {
        return new ErrorResponse("Requisição possui campos inválidos", status.value(),
                status.getReasonPhrase(), ex.getBindingResult().getObjectName(), errors);
    }

    private List<Error> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new Error(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }
    
}
*/