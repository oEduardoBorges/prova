package com.oEduardoBorges.dto.response.user;

import com.oEduardoBorges.model.User;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String nome;
    private String email;
    private String username;

    public UserResponse(User entity) {
        nome = entity.getNome();
        email = entity.getEmail();
        username = entity.getUsername();
    }
}
