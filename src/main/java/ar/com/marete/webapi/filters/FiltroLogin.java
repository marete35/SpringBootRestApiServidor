package ar.com.marete.webapi.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.marete.webapi.mappings.Usuario;
import ar.com.marete.webapi.servicios.HttpParserService;

public class FiltroLogin extends AbstractAuthenticationProcessingFilter{

	private HttpParserService httpParserService;
	
	
	public FiltroLogin(String url ,AuthenticationManager manager) {
		
		super(url);
		httpParserService= new HttpParserService();
		setAuthenticationManager(manager);
		
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		
		
	
		
		
		Usuario usuario = new ObjectMapper().readValue(request.getInputStream(),Usuario.class);
		System.out.println(usuario.getNombre());
		System.out.println(usuario.getPassword());
		UsernamePasswordAuthenticationToken usuarioSpring = new UsernamePasswordAuthenticationToken(usuario.getNombre(), usuario.getPassword());
		return getAuthenticationManager().authenticate(usuarioSpring);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication autentication) throws IOException, ServletException {
		
		String nombreUsuario= autentication.getName();
		httpParserService.creaToken(response, nombreUsuario);
		
	}
	
	
	
	

}
