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

import com.example.security.constant.AuthControllerURIs;
import com.example.security.constant.TestControllerURIs;
import com.example.security.constant.UserPermissions;

@Configuration
public class SecurityConfig {

	// Dependencies

	@Autowired
	private SecurityFilter securityFilter;

	// Methods

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http
			.formLogin(form -> {
				form.disable();
			})
			.logout(logout -> {
				logout.disable();
			})
			.httpBasic(hb -> {
				hb.disable();
			})
			.csrf(c -> {
				c.disable();
			})
			.sessionManagement(sm -> {
				sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			})
			.authorizeHttpRequests(req -> {
				req.requestMatchers(HttpMethod.POST, AuthControllerURIs.login).permitAll();
				req.requestMatchers(HttpMethod.GET, TestControllerURIs.denyAll).denyAll();
				req.requestMatchers(HttpMethod.GET, TestControllerURIs.permitAll).permitAll();
				req.requestMatchers(HttpMethod.GET, TestControllerURIs.permitAllAuthenticated).authenticated();
				req.requestMatchers(HttpMethod.GET, TestControllerURIs.permitAuthenticatedAdmin).hasAuthority(UserPermissions.ADMIN);
				req.requestMatchers(HttpMethod.GET, TestControllerURIs.permitAuthenticatedManager).hasAuthority(UserPermissions.MANAGER);
				req.requestMatchers(HttpMethod.GET, TestControllerURIs.permitAuthenticatedCommonUser).hasAuthority(UserPermissions.COMMON_USER);
				req.requestMatchers(HttpMethod.GET, TestControllerURIs.permitAuthenticatedAdminOrManager).hasAnyAuthority(
					UserPermissions.ADMIN,
					UserPermissions.MANAGER
				);
				req.anyRequest().denyAll();
			})
			.exceptionHandling(e -> {
				e.accessDeniedHandler(accessDeniedHandler());
				e.authenticationEntryPoint(authenticationEntryPointHandler());
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

	@Bean
	CustomAccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	CustomAuthenticationEntryPointHandler authenticationEntryPointHandler() {
		return new CustomAuthenticationEntryPointHandler();
	}

	@Bean
	CustomAuthenticationFailureHandler authenticationFailureHandler() {
		//TODO add to security filter
		return new CustomAuthenticationFailureHandler();
	}
	
	@Bean
	CustomAuthenticationSuccessHandler authenticationSuccessHandler() {
		//TODO add to security filter
		return new CustomAuthenticationSuccessHandler();
	}

}
