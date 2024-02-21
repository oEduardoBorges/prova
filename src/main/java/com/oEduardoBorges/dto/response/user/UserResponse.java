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
    private String name;
    private String email;
    private String username;

    public UserResponse(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        username = entity.getUsername();
    }

    public UserResponse(UserRequestUpdate userRequestUpdate) {
        name = userRequestUpdate.getName();
        email = userRequestUpdate.getEmail();
        username = userRequestUpdate.getUsername();
    }
}
