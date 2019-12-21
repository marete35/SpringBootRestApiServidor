package ar.com.marete.Basic.Api.Servidor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import ar.com.marete.basic.servicios.TokenService;

public class TokenTest {

	@Test
	public void testCrearToken() throws ParseException {
		TokenService servicio = new TokenService();
		DateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String token = servicio.creaToken("mario", "miclave", formateador.parse("01/03/2020"));
		System.out.println(token);
		
		assertEquals("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJpbyIsImV4cCI6MTU4MzAzMTYwMH0.NMyGNGw_4xt1N4Dmd1zJXadKUWX9SjDtysvuj9vMBwE", 
				     "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJpbyIsImV4cCI6MTU4MzAzMTYwMH0.NMyGNGw_4xt1N4Dmd1zJXadKUWX9SjDtysvuj9vMBwE");
	}
	
	@Test
	public void testLeerUsuario() {
		TokenService servicio = new TokenService();
		String usuario = servicio.leeToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJpbyIsImV4cCI6MTU4MzAzMTYwMH0.NMyGNGw_4xt1N4Dmd1zJXadKUWX9SjDtysvuj9vMBwE"
, "miclave");
		System.out.println(usuario);
		assertEquals("mario", usuario);
		
	}
}
