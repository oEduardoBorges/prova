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

    public UserResponse(User user) {
        id = user.getId();
        nome = user.getName();
        email = user.getEmail();
        username = user.getUsername();
    }

    public UserResponse(UserRequestUpdate userRequestUpdate) {
        nome = userRequestUpdate.getName();
        email = userRequestUpdate.getEmail();
        username = userRequestUpdate.getUsername();
    }
}
