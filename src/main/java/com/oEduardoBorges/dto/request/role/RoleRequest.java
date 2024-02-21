package com.oEduardoBorges.dto.request.role;

import com.oEduardoBorges.model.Role;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleRequest {

    private String authority;

    public RoleRequest(Role role) {
        this.authority = role.getAuthority();
    }
}
