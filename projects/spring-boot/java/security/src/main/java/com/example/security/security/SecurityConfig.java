package com.example.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.security.enums.UserPermissions;

@Configuration
public class SecurityConfig {

	// Dependencies

	@Autowired
	private SecurityFilter securityFilter;

	// Methods

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http
			.httpBasic(hb -> {
				hb.disable();
			})
			.csrf(csrf -> {
				csrf.disable();
			})
			.sessionManagement(sm -> {
				sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			})
			.authorizeHttpRequests(req -> {
				req.requestMatchers(HttpMethod.POST, "/auth").permitAll();
				req.requestMatchers(HttpMethod.GET, "/test/1").denyAll();
				req.requestMatchers(HttpMethod.GET, "/test/2").permitAll();
				req.requestMatchers(HttpMethod.GET, "/test/3").authenticated();
				req.requestMatchers(HttpMethod.GET, "/test/4").hasAuthority(UserPermissions.COMMON_USER.toString());
				req.requestMatchers(HttpMethod.GET, "/test/5").hasAuthority(UserPermissions.ADMIN.toString());
				req.requestMatchers(HttpMethod.GET, "/test/6").hasAnyAuthority(
					UserPermissions.ADMIN.toString(),
					UserPermissions.COMMON_USER.toString()
				);
				req.anyRequest().denyAll();
			})
			.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
			.build()
		;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
