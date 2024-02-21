package com.oEduardoBorges.dto.request.user;

import com.oEduardoBorges.model.User;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestUpdate {

    private String nome;
    private String email;
    private String username;
    private String password;

    public UserRequestUpdate(User user) {
        nome = user.getNome();
        email = user.getEmail();
        username = user.getUsername();
        password = user.getPassword();
    }
}
