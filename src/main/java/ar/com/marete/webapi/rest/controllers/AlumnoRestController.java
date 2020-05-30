package ar.com.marete.webapi.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import ar.com.marete.business.AlumnoBusiness;

@RestController
@RequestScope
@RequestMapping("webapi")
@CrossOrigin(origins = "http://localhost:8081", methods = {RequestMethod.GET,RequestMethod.POST})
public class AlumnoRestController extends BaseRestController {
	
	@Autowired
	private AlumnoBusiness alumnoBusiness;
	
	@GetMapping("/alumnos")
	public @ResponseBody ResponseEntity<String> getAlumnos(){
		alumnoBusiness.getAlumnos();
		return alumnoBusiness.getOutput(); 
	}

}
