package br.com.mars.infrastructure.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.mars.infrastructure.exceptions.EntityModelNotFoundException;
import br.com.mars.infrastructure.exceptions.ResourceNotFoundException;
import br.com.mars.infrastructure.exceptions.SpaceAlreadyOccupiedException;
import br.com.mars.infrastructure.service.impl.MessageServiceImpl;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	protected MessageServiceImpl messageService;

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
		log.error("INTERNAL_SERVER_ERROR", ex);
		return handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR, request, "internal.server.error");
	}

	@ExceptionHandler({ EntityModelNotFoundException.class })
	public ResponseEntity<Object> handleEntityModelNotFoundException(RuntimeException ex, WebRequest request) {
		return handleException(ex, HttpStatus.NOT_FOUND, request, "entity.model.notfound");
	}

	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex, WebRequest request) {
		return handleException(ex, HttpStatus.NOT_FOUND, request, ex.getMessage() + " " + "resource.notfound");
	}

	@ExceptionHandler({ SpaceAlreadyOccupiedException.class })
	public ResponseEntity<Object> handleSpaceAlreadyOccupiedException(RuntimeException ex, WebRequest request) {
		return handleException(ex, HttpStatus.CONFLICT, request, "space.already.occupied");
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> erros = getErrorList(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}

	protected List<String> getErrorList(BindingResult bindingResult) {
		List<String> erros = new ArrayList<>();
		bindingResult.getFieldErrors().forEach(e -> erros.add(e.getField() + " " + messageService.getMessage(e)));
		return erros;
	}

	protected ResponseEntity<Object> handleException(Exception ex, HttpStatus status, WebRequest req, String chave) {
		List<String> errors = (Arrays.asList((messageService.getMessage(chave))));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), status, req);
	}
}
