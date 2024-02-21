package com.oEduardoBorges.controller;

import com.oEduardoBorges.dto.request.tela.TelaRequest;
import com.oEduardoBorges.dto.response.tela.TelaResponse;
import com.oEduardoBorges.dto.response.user.UserResponse;
import com.oEduardoBorges.service.TelaService;
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

    @GetMapping
    public ResponseEntity<List<TelaResponse>> userList() {
        return ResponseEntity.status(HttpStatus.OK).body(telaService.telaList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TelaResponse>> telaById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(telaService.findTelaById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<TelaResponse> createTela(@RequestBody TelaRequest telaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(telaService.createTela(telaRequest));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TelaResponse> telaUpdate(@PathVariable Long id, @RequestBody TelaRequest telaRequest){
        TelaResponse telaResponse = telaService.telaUpdate(id, telaRequest);
        return ResponseEntity.status(HttpStatus.OK).body(telaResponse);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        telaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
