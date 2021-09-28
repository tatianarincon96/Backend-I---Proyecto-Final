package com.dh.clinica.persistence.repository;
import com.dh.clinica.persistence.entity.Paciente;
import com.dh.clinica.persistence.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente,Integer> {
    Optional<Paciente> findByDni(String dni);
}
