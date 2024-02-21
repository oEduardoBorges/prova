package com.oEduardoBorges.service;

import com.oEduardoBorges.dto.response.user.UserResponse;
import com.oEduardoBorges.model.User;
import com.oEduardoBorges.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Page<UserResponse> userList(Pageable pageable) {
         Page<User> list = userRepository.findAll(pageable);
        return list.map(UserResponse::new);
    }
}
