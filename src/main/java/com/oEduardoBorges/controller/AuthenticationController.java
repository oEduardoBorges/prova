package com.oEduardoBorges.controller;

import com.oEduardoBorges.dto.request.AuthenticationRequest;
import com.oEduardoBorges.dto.request.RegisterRequest;
import com.oEduardoBorges.dto.response.AuthenticationResponse;
import com.oEduardoBorges.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @Operation(summary = "Se cadastrar no sistema")
  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody @Valid RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }

  @Operation(summary = "Logar no sistema.")
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }


}
