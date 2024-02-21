package com.oEduardoBorges.dto.response.role;

import com.oEduardoBorges.model.Role;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleResponse {

    private Long id;
    private String authority;

    public RoleResponse(Role role) {
        this.id = role.getId();
        this.authority = role.getAuthority();
    }
}
