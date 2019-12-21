package ar.com.marete.Basic.rest.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.marete.Basic.entidades.Persona;
import ar.com.marete.Basic.repository.PersonaRepository;

@RestController
@RequestMapping("webapi")
@CrossOrigin(origins = "http://localhost:8081", methods = {RequestMethod.GET,RequestMethod.POST})
public class PersonaRestController {
	
	@Autowired
	PersonaRepository repositorio;
	
	@GetMapping("/personas")
	public Iterable<Persona> buscarTodos(){
		return repositorio.findAll();
	}
	
	@PostMapping(path="/personas", consumes="application/json")
	public void insertar(@RequestBody Persona persona) {
		repositorio.save(persona);
	}
	
	@DeleteMapping("/personas/{nombre}")
	public void borrar(@PathVariable String nombre) {
	Optional<Persona> persona=repositorio.findById(nombre);
		
		if (persona.isPresent()) {
			repositorio.delete(persona.get());
			
		}
		
	}

}
