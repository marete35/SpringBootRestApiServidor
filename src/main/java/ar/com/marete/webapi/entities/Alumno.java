package ar.com.marete.webapi.entities;

public class Alumno extends Persona{

	private Integer idAlumno;
	private Integer numero;
	private Integer idGrado;
	private String email;
	
	public Integer getIdAlumno() {
		return idAlumno;
	}
	
	public void setIdAlumno(Integer idAlumno) {
		this.idAlumno = idAlumno;
	}
	
	public Integer getNumero() {
		return numero;
	}
	
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public Integer getIdGrado() {
		return idGrado;
	}
	
	public void setIdGrado(Integer idGrado) {
		this.idGrado = idGrado;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
