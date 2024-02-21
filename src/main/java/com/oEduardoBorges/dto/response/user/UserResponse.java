package com.oEduardoBorges.dto.response.user;

import com.oEduardoBorges.dto.request.user.UserRequestUpdate;
import com.oEduardoBorges.model.User;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String nome;
    private String email;
    private String username;

    public UserResponse(User entity) {
        id = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();
        username = entity.getUsername();
    }

    public UserResponse(UserRequestUpdate userRequestUpdate) {
        nome = userRequestUpdate.getNome();
        email = userRequestUpdate.getEmail();
        username = userRequestUpdate.getUsername();
    }
}
