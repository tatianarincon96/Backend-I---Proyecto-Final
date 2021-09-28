package com.dh.clinica.persistence.repository;
import com.dh.clinica.persistence.entity.Turno;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno,Integer> {

}
