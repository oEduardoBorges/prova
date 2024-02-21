package com.oEduardoBorges.controller;

import com.oEduardoBorges.dto.request.role.RoleRequest;
import com.oEduardoBorges.dto.response.role.RoleResponse;
import com.oEduardoBorges.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleResponse>> roleList() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.roleList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<RoleResponse>> roleById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.findRoleById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<RoleResponse> createRole(@RequestBody RoleRequest roleRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(roleRequest));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<RoleResponse> roleUpdate(@PathVariable Long id, @RequestBody RoleRequest roleRequest){
        RoleResponse roleResponse = roleService.RoleUpdate(id, roleRequest);
        return ResponseEntity.status(HttpStatus.OK).body(roleResponse);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        roleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
