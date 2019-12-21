package ar.com.marete.Basic.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import ar.com.marete.Basic.filters.FilterLogin;
import ar.com.marete.Basic.filters.FiltroJWTAutenticacion;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//sino hacemos este paso, spring security no funciona correctamente. 
		PasswordEncoder codificador = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication()
			.withUser("mario")
			.password(codificador.encode("miclave"))
			.roles("ADMINISTRADOR");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//es un patron que viene de ant que es una herramienta clasica.
		//es un patron para urls.
		http.csrf()
			.disable()
			.cors()
			.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST,"/webapi/login")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.addFilterBefore(new FilterLogin("/webapi/login",authenticationManager()),UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new FiltroJWTAutenticacion(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.addAllowedOrigin("http://localhost:8081");
		configuration.addAllowedHeader("*");
		configuration.addExposedHeader("Authorization");
		configuration.addAllowedMethod("*");
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	
}
