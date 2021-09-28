package com.dh.clinica.persistence.repository;
import com.dh.clinica.persistence.entity.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IODomicilioRepository extends JpaRepository<Domicilio,Integer> {

    @Query("SELECT d FROM Domicilio d WHERE d.calle like ?1 and d.numero like ?2")
    Domicilio findIdByStreetAndNumber(String calle, String numero);

}
