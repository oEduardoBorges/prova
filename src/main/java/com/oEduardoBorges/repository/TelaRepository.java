package com.oEduardoBorges.repository;

import com.oEduardoBorges.model.Tela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelaRepository extends JpaRepository<Tela, Long> {

    Boolean existsByName(String name);
}
