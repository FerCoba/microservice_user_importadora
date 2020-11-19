package com.microservice.user.importadora.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.user.importadora.response.Response;

public enum EnumExceptionsHandler {

	THROW_EXCEPTION("NoHandlerFoundException", "NoSuchElementException", "NullPointerException",
			"AccessDeniedException", "NumberFormatException", "IllegalArgumentException","UserException") {

		private Object conversion(Object obj) {

			ObjectMapper jsonMapper = new ObjectMapper();
			String resultado = null;
			try {
				resultado = jsonMapper.writeValueAsString(obj);
			} catch (JsonProcessingException e) {
				e.getMessage();
			}

			return resultado;
		}

		public ResponseEntity<Object> exceptionBuilder(Exception ex, WebRequest wr, String tipoException) {
			Response resp = null;
			if (THROW_EXCEPTION.getValues().contains((tipoException))) {

				switch (tipoException) {
				case "NoHandlerFoundException":
					resp = new Response(String.valueOf(HttpStatus.NOT_FOUND.value()), "The resource don't exist.",
							null);
					return new ResponseEntity<>(conversion(resp), HttpStatus.NOT_FOUND);
				case "AccessDeniedException":
					resp = new Response(String.valueOf(HttpStatus.UNAUTHORIZED.value()),
							"You haven't authorization for access to the resource.", null);
					return new ResponseEntity<>(conversion(resp), HttpStatus.UNAUTHORIZED);
				case "UserException":
					resp = new Response(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage(), null);
					return new ResponseEntity<>(conversion(resp), HttpStatus.BAD_REQUEST);
				default:
					resp = new Response(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
							"In this moment we can't attend your request. Try again in a few minutes.", null);
					return new ResponseEntity<>(conversion(resp), HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			return new ResponseEntity<>(conversion(resp), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	};

	private final List<String> values;

	EnumExceptionsHandler(String... values) {
		this.values = Arrays.asList(values);
	}

	public List<String> getValues() {
		return values;
	}

	public abstract ResponseEntity<Object> exceptionBuilder(Exception ex, WebRequest wr, String tipoException);

}
