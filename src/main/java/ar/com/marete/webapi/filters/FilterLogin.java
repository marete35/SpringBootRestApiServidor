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

public class FilterLogin extends AbstractAuthenticationProcessingFilter{
	
	private HttpParserService httpParserService;

	/**
	 * Constructor de la clase. 
	 * @param url url para loguearse. 
	 * @param manager
	 */
	public FilterLogin(String url, AuthenticationManager manager) {
		super(url);
		HttpParserService httpParserService = new HttpParserService();
		setAuthenticationManager(manager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)//intentar autenticarse.
			throws AuthenticationException, IOException, ServletException {
		//lee el usuario y la clave de una peticion post en la cual los adjuntemos via json.
		Usuario usuario = new ObjectMapper().readValue(request.getInputStream(),Usuario.class);
		UsernamePasswordAuthenticationToken usuarioSpringToken = new UsernamePasswordAuthenticationToken(usuario.getNombre(), usuario.getNombre());
		return getAuthenticationManager().authenticate(usuarioSpringToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		
		String nombreUsuario = authentication.getName();
		httpParserService.creaToken(response, nombreUsuario);
	}

	
}
