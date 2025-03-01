package com.cursojavanauta.spring_learn.infrastructure.repository;

import com.cursojavanauta.spring_learn.infrastructure.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {


}
