package ar.com.marete.webapi.servicios;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import ar.com.marete.webapi.mappings.UsuarioAutentificado;

public class HttpParserService {private long expiracion = 36000000; //esto es el tiempo de expiracion del token 
private String claveEncriptar = "superclave";

// que es la que se denomina portador nos identifica el portador 
private String prefijoToken = "Bearer";

//la cabecera en donde vamos a ubicar el token que es la cabecera de authorization
private String cabeceraHttp = "Authorization";


//private TokenService tokenService; //usar el propio servicio de token que hemos desarrollado



public void creaToken(HttpServletResponse response , String usuario) {
	
	String JWT = new TokenService().creaToken(usuario,claveEncriptar,new Date (System.currentTimeMillis()+expiracion));
	
	response.addHeader(cabeceraHttp, prefijoToken + " " + JWT);
   
	
}

public Authentication leeToken (HttpServletRequest request) {
	
	
	 String token = request.getHeader(cabeceraHttp);
	 //simplemente el hash completo del token
	
	 
	 if (token!=null) {
		 String tokenReal=token.substring(token.indexOf(" ")+1);
		 
		 //asignar correctamente el token service a la hora de leer el token
		 String usuario =new TokenService().leeToken(tokenReal,claveEncriptar);
		 if(usuario!=null) {
			 
			 // hay q devolver un objeto ya de Spring framework concretamente de la parte de seguridad
			 // esto es ya una problematica de integracion
			 // como lo gestionamos???
		
			 
			 return new UsuarioAutentificado(usuario);
			 
			 
		 }
		 
	 }
	 
	 
	return null;
	
}
}
