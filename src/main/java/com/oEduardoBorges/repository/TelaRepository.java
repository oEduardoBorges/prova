package com.oEduardoBorges.repository;

import com.oEduardoBorges.model.Tela;
import com.oEduardoBorges.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelaRepository extends JpaRepository<Tela, Long> {

    Boolean existsByName(String name);
}
