package com.oEduardoBorges.service;

import com.oEduardoBorges.dto.request.role.RoleRequest;
import com.oEduardoBorges.dto.response.role.RoleResponse;
import com.oEduardoBorges.model.Role;
import com.oEduardoBorges.repository.RoleRepository;
import com.oEduardoBorges.service.exceptions.DatabaseException;
import com.oEduardoBorges.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<RoleResponse> roleList() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(RoleResponse::new).toList();
    }

    public Optional<RoleResponse> findRoleById(Long id) {
        Optional<Role> roleById = Optional.ofNullable(roleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Permissão não encontrado")));
        return roleById.map(RoleResponse::new);
    }

    public RoleResponse createRole(RoleRequest roleRequest) {
        boolean exists = roleRepository.existsByAuthority(roleRequest.getAuthority());

        if(exists){
            throw new DatabaseException("Permissao já existente");
        }
        Role role = roleRepository.save(new Role(roleRequest));
        return new RoleResponse(role);
    }

    @Transactional
    public RoleResponse RoleUpdate(Long id, RoleRequest roleRequest) {
        try {
            Role role = roleRepository.getReferenceById(id);
            role.setAuthority(roleRequest.getAuthority());
            role = roleRepository.save(role);
            return new RoleResponse(role);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id não encontrado" + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Permissão não encontrado");
        }
        try {
            roleRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
