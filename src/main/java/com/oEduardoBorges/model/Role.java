package com.oEduardoBorges.model;

import com.oEduardoBorges.dto.request.role.RoleRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

  @NotEmpty
  private String authority;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "tb_role_tela",
          joinColumns = @JoinColumn(name = "role_id"),
          inverseJoinColumns = @JoinColumn(name = "tela_id"))
  private Set<Tela> telas = new HashSet<>();

  public Role(Long id, String authority) {
    this.id = id;
    this.authority = authority;
  }

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

