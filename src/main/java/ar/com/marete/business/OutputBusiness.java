package ar.com.marete.business;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import ar.com.marete.webapi.entities.Error;
import ar.com.marete.webapi.entities.Output;

public class OutputBusiness {
	
	private Output out = new Output();
	private String format = "json"; //output format: json or xml
	private HttpStatus httpResponseStatusCode = HttpStatus.OK; //200 ok
	private HttpHeaders responseHeaders = new HttpHeaders();	
	
	public ResponseEntity<String> getOutput(){
		ResponseEntity<String> output = this.formatOutput();
		this.cleanOutput();
		return output;
	}
	
	public void setOutData(Object data) {
		out.setData(data);
	}
	
	private ResponseEntity<String> formatOutput(){
		String output = "";
		if (this.format.contentEquals("xml")) {
			ObjectMapper xmlMapper = new XmlMapper();
			responseHeaders.setContentType(MediaType.APPLICATION_XML);
			try {
				output = xmlMapper.writeValueAsString(out);
			}catch(JsonProcessingException e) {
				this.httpResponseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
				out.setMessage(e.getMessage());
				out.setCode(500);
			}

		}else {
			ObjectMapper mapper = new ObjectMapper();
			responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
			this.setHttpResponseStatusCode(HttpStatus.OK);
			try {
				output = mapper.writeValueAsString(out);
			}catch(JsonProcessingException e) {
				Error error = new Error(-1,null,e.getMessage());
				this.addError(error);
				this.setHttpResponseStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
				this.setCode(500); 
				this.setOutMessage("Error al serializar la respuesta");
				this.setOutData(null);
				try {
					output = mapper.writeValueAsString(out);
				}catch(JsonProcessingException e1) {
					System.out.println("Error generando la respuesta");
				}
			}
		}
		return new ResponseEntity<String>(output,responseHeaders,httpResponseStatusCode);
	}
	
	public void setHttpResponseStatusCode(HttpStatus httpResponseStatusCode) {
		this.httpResponseStatusCode = httpResponseStatusCode;
	}
	
	public void addError(Error er) {
		out.addError(er);
	}
	
	public void setCode(int code) {
		out.setCode(code);;
	}
	
	public void setOutMessage(String message) {
		out.setMessage(message);
	}
	
	private void cleanOutput() {
		this.format = "json";
		this.httpResponseStatusCode = HttpStatus.OK;
		this.responseHeaders = new HttpHeaders();
		this.out.cleanOutput();
	}

}
