package com.tamalitos.hemeroteca_catalogo;

import com.tamalitos.hemeroteca_catalogo.auth.JWTAuthorizationFilter;

import java.time.Duration;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication
public class HemerotecaCatalogoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HemerotecaCatalogoApplication.class, args);
	}

	@Configuration
	public class SecurityConfiguration {
		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable()
					.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/admin/login").permitAll()
					.antMatchers(HttpMethod.GET, "/publicacion/hemeroteca/{id}").permitAll()
					.antMatchers(HttpMethod.GET, "/hemeroteca/completa").permitAll()
					.anyRequest().authenticated();
			return http.build();
		}

		@Bean
		CorsConfigurationSource corsConfigurationSource() {
			CorsConfiguration cc = new CorsConfiguration();
			cc.setAllowedHeaders(
					Arrays.asList("Origin,Accept", "X-Requested-With", "Content-Type", "Access-Control-Allow-Origin",
							"Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization"));
			cc.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
			cc.setAllowedOrigins(Arrays.asList("/*"));
			cc.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
			cc.addAllowedOrigin("http://localhost:4200");
			cc.setMaxAge(Duration.ZERO);
			cc.setAllowCredentials(Boolean.TRUE);
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", cc);
			return source;
		}
	}

}
