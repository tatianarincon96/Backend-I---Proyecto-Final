package com.dh.clinica.persistence.repository;

import com.dh.clinica.persistence.entity.Odontologo;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo,Integer> {

}
