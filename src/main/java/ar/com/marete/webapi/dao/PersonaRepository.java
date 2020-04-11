package ar.com.marete.webapi.dao;

import org.springframework.data.repository.CrudRepository;

import ar.com.marete.webapi.entidades.Persona;

public interface PersonaRepository extends CrudRepository<Persona,String>{

	

}