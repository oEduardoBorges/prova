package com.oEduardoBorges.service;

import com.oEduardoBorges.dto.request.AuthenticationRequest;
import com.oEduardoBorges.dto.response.AuthenticationResponse;
import com.oEduardoBorges.dto.request.RegisterRequest;
import com.oEduardoBorges.config.JwtService;
import com.oEduardoBorges.model.Role;
import com.oEduardoBorges.model.User;
import com.oEduardoBorges.repository.UserRepository;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
        .name(request.getNome())
        .email(request.getEmail())
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .roles(new HashSet<>(Arrays.asList(new Role(2L, "ROLE_CLIENT"))))
        .build();
    userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );
    var user = userRepository.findByUsername(request.getUsername())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  protected User authenticated(){
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
      String username = String.valueOf(jwtPrincipal.getHeader());

      return userRepository.findByEmail(username).get();

    }catch (Exception e){
      throw new UsernameNotFoundException("Email não encontrado.");
    }
  }
}
