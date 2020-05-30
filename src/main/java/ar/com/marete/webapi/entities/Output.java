package ar.com.marete.webapi.entities;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase que representa el json que se retornara al cliente (navegador web)
 * @author Marete Ramones
 *
 */
public class Output {

	private int code = 0;
	private String message = "OK";
	private Object data;
	private List<Error> errors = new ArrayList<Error>();
	
	public Output() {
		this.code = 0;
		this.message = "OK";
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	
	public void addError(Error error) {
		this.errors.add(error);
	}
	
	public void cleanOutput() {
		this.code = 0;
		this.message = "OK";
		this.data = null;
		this.errors = new ArrayList<Error>();
	}
}
