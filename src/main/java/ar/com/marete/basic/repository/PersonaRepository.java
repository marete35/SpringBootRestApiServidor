package ar.com.marete.basic.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.marete.basic.entidades.Persona;

public interface PersonaRepository extends CrudRepository<Persona,String>{

	

}