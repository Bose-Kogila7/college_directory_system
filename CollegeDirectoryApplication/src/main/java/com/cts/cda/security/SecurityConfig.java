package com.cts.cda.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

//	@Autowired
//	 private UserDetailsService userDetailsService;

	 @Bean
	    public PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(
	                                 AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	    }

	    @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            http.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests((authorize) ->
                            //authorize.anyRequest().authenticated()
                            authorize
                                    .requestMatchers("/api/auth/**").permitAll()
//                                    .requestMatchers("/api/admin/**").hasRole("student")
//                                    .requestMatchers("/api/admin/**").hasRole("faculty")
//                                    .requestMatchers(HttpMethod.POST, "/admin/add-Student").hasRole("student") 
//                                    .requestMatchers(HttpMethod.POST, "/admin/add-Faculty").hasRole("faculty")
                                    .anyRequest().authenticated()

                    ).httpBasic(Customizer.withDefaults());

	        return http.build();
	    }
}

