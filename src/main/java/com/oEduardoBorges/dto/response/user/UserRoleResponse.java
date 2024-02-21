package com.oEduardoBorges.dto.response.user;

import com.oEduardoBorges.dto.request.user.UserRequestUpdate;
import com.oEduardoBorges.dto.response.role.RoleResponse;
import com.oEduardoBorges.model.Role;
import com.oEduardoBorges.model.User;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleResponse {

    private Long id;
    private String name;
    private String email;
    private String username;
    private Set<RoleResponse> roles = new HashSet<>();

    public UserRoleResponse(UserRequestUpdate userRequestUpdate) {
        name = userRequestUpdate.getName();
        email = userRequestUpdate.getEmail();
        username = userRequestUpdate.getUsername();
    }

    public UserRoleResponse(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        username = user.getUsername();
        for (Role role : user.getRoles()){
            roles.add(new RoleResponse(role));
        }
    }
}
