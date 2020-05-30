package ar.com.marete.webapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Error {
	
	private int code;
	@JsonInclude(value=Include.NON_DEFAULT)
	private String field;
	private String message;
	@JsonInclude(value=Include.NON_DEFAULT)
	private String descripcion;
	
	public Error() {
		
	}

	public Error(int code, String field, String message) {
		super();
		this.code = code;
		this.field = field;
		this.message = message;
	}

	public Error(int code, String field, String message, String descripcion) {
		super();
		this.code = code;
		this.field = field;
		this.message = message;
		this.descripcion = descripcion;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
