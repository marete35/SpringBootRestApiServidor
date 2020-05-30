package ar.com.marete.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ar.com.marete.webapi.dao.AlumnoDAO;

@Service
public class AlumnoBusiness extends BaseBusiness{

	@Autowired
	private AlumnoDAO alumnoDAO;
	
	public void getAlumnos(){
		try {
			super.out.setOutData(alumnoDAO.getAlumnos());
		}catch(EmptyResultDataAccessException e) {
			super.createException(e);
		}
		
	}

}
