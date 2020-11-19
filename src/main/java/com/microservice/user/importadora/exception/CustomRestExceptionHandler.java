package com.microservice.user.importadora.exception;

import java.nio.file.AccessDeniedException;
import java.security.InvalidParameterException;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@EnableWebMvc
@ControllerAdvice
@RestController
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LogManager.getLogger(CustomRestExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
																   final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.info("Enter into method handleNoHandlerFoundException of class ResponseEntityExceptionHandler.");
		return EnumExceptionsHandler.THROW_EXCEPTION.exceptionBuilder(ex, request, ex.getClass().getSimpleName());

	}

	@ExceptionHandler({ Exception.class, RequestRejectedException.class, NullPointerException.class,
			NoSuchElementException.class, AccessDeniedException.class, InvalidParameterException.class, UserException.class, InstantiationException.class,
			IllegalAccessException.class})
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	protected ResponseEntity<Object> handleAll(final Exception ex, WebRequest request) {
		logger.info("Enter into method handleAll of class ResponseEntityExceptionHandler.");
		return EnumExceptionsHandler.THROW_EXCEPTION.exceptionBuilder(ex, request, ex.getClass().getSimpleName());
	}

}
