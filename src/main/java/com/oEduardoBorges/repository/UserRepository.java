package com.oEduardoBorges.repository;

import java.util.Optional;

import com.oEduardoBorges.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

}
