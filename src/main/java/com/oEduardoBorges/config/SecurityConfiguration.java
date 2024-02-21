package com.oEduardoBorges.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf()
        .disable()
        .authorizeHttpRequests()
        .requestMatchers(
                "/auth/**",
                "/v2/api-docs",
                "/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui/**",
                "/webjars/**",
                "/swagger-ui.html"
        )
        .permitAll()
        .requestMatchers(DELETE, "/user/**").hasAnyAuthority("ROLE_ADMIN")
            .requestMatchers(PUT, "/user/**").hasAnyAuthority("ROLE_ADMIN")
            .requestMatchers(POST, "/user/**").hasAnyAuthority("ROLE_ADMIN")
        .requestMatchers(DELETE, "/role/**").hasAnyAuthority("ROLE_ADMIN")
          .requestMatchers(PUT, "/role/**").hasAnyAuthority("ROLE_ADMIN")
            .requestMatchers(POST, "/role/**").hasAnyAuthority("ROLE_ADMIN")
        .requestMatchers(DELETE, "/tela/**").hasAnyAuthority("ROLE_ADMIN")
            .requestMatchers(PUT, "/tela/**").hasAnyAuthority("ROLE_ADMIN")
              .requestMatchers(POST, "/tela/**").hasAnyAuthority("ROLE_ADMIN")
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
