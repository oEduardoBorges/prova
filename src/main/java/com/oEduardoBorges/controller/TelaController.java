package com.oEduardoBorges.controller;

import com.oEduardoBorges.dto.request.tela.TelaRequest;
import com.oEduardoBorges.dto.response.tela.TelaResponse;
import com.oEduardoBorges.service.TelaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tela")
@RequiredArgsConstructor
public class TelaController {

    private final TelaService telaService;

    @Operation(summary = "Lista de telas.")
    @GetMapping
    public ResponseEntity<List<TelaResponse>> userList() {
        return ResponseEntity.status(HttpStatus.OK).body(telaService.telaList());
    }

    @Operation(summary = "Buscar uma tela por ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TelaResponse>> telaById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(telaService.findTelaById(id));
    }

    @Operation(summary = "Criar uma nova tela.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<TelaResponse> createTela(@RequestBody TelaRequest telaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(telaService.createTela(telaRequest));
    }

    @Operation(summary = "Atualizar uma tela.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TelaResponse> telaUpdate(@PathVariable Long id, @RequestBody TelaRequest telaRequest){
        TelaResponse telaResponse = telaService.telaUpdate(id, telaRequest);
        return ResponseEntity.status(HttpStatus.OK).body(telaResponse);
    }

    @Operation(summary = "Deletar uma tela.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        telaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
