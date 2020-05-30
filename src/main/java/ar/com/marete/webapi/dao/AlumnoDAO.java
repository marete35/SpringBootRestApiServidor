package ar.com.marete.webapi.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import ar.com.marete.webapi.entities.Alumno;

@Repository
public class AlumnoDAO extends BaseDAO{
	
	public List<Alumno> getAlumnos(){
		return null;
	}

}
