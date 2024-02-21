package com.oEduardoBorges.service;

import com.oEduardoBorges.dto.request.user.UserRequestUpdate;
import com.oEduardoBorges.dto.response.user.UserResponse;
import com.oEduardoBorges.model.User;
import com.oEduardoBorges.repository.UserRepository;
import com.oEduardoBorges.service.exceptions.DatabaseException;
import com.oEduardoBorges.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Page<UserResponse> userList(Pageable pageable) {
        Page<User> list = userRepository.findAll(pageable);
        return list.map(UserResponse::new);
    }

    @Transactional
    public UserResponse userUpdate(Long id, UserRequestUpdate userUpdate) {
        try {
            User user = userRepository.getReferenceById(id);
            user.setName(userUpdate.getName());
            user.setUsername(userUpdate.getUsername());
            user.setEmail(userUpdate.getName());
            String encodedPassword = passwordEncoder.encode(userUpdate.getPassword());
            user.setPassword(encodedPassword);
            user = userRepository.save(user);
            return new UserResponse(user);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id não encontrado" + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
        try {
            userRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
