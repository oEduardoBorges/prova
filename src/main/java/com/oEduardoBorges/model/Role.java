package com.oEduardoBorges.model;

import com.oEduardoBorges.dto.request.role.RoleRequest;
import com.oEduardoBorges.dto.request.tela.TelaRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String authority;

  public Role(RoleRequest roleRequest) {
    this.authority = roleRequest.getAuthority();
  }

  @Override
  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Role role = (Role) o;
    return Objects.equals(authority, role.authority);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authority);
  }
}

