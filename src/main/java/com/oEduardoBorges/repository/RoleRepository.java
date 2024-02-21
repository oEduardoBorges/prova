package com.oEduardoBorges.repository;

import com.oEduardoBorges.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Boolean existsByAuthority(String authority);
}
