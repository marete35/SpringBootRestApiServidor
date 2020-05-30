package ar.com.marete.business;

import org.springframework.http.ResponseEntity;

public class BaseBusiness {
	
	protected OutputBusiness out;

	public OutputBusiness getOut() {
		return out;
	}
	
	public ResponseEntity<String> getOutput(){
		return this.out.getOutput();
	}

	public void setOut(OutputBusiness out) {
		this.out = out;
	}
	
	public void createException(Exception e) {
		
	}

}
