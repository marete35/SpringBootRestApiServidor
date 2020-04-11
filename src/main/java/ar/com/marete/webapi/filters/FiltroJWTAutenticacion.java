 package ar.com.marete.webapi.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import ar.com.marete.webapi.servicios.HttpParserService;

public class FiltroJWTAutenticacion extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//asumimos que recibimos un token, que es nuestro token jwt con el usuario que necesitamos.
		Authentication authentication = new HttpParserService().leeToken((HttpServletRequest) request);
		
		//asigna token de autenticacion que acabmos de validar con el servicio httpparser
		SecurityContextHolder.getContext().setAuthentication(authentication); 
		
		chain.doFilter(request,response);
	}
	
	

}
