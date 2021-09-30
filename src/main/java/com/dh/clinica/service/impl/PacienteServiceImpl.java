package com.dh.clinica.service.impl;

import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.persistence.entity.Paciente;
import com.dh.clinica.persistence.repository.IPacienteRepository;
import com.dh.clinica.service.IPacienteService;
import com.dh.clinica.exceptions.FindByIdException;
import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("PacienteServiceImpl")
public class PacienteServiceImpl implements IPacienteService {

    private final Logger logger = Logger.getLogger(PacienteServiceImpl.class);
    @Autowired
    private IPacienteRepository pacienteRepository;

    public PacienteServiceImpl(IPacienteRepository pacienteRepository) {}

    @Override
    public PacienteDto registrar(PacienteDto paciente) {
        logger.debug("Iniciando método registrar paciente");
        paciente.setFechaIngreso(LocalDate.now());
        Paciente p = pacienteRepository.save(paciente.toEntity());
        logger.debug("Terminó la ejecución del método registrar paciente");
        return new PacienteDto(p);
    }

    @Override
    public PacienteDto buscar(Integer id) throws FindByIdException {
        logger.debug("Ejecutando método buscar paciente por ID");
        if (!pacienteRepository.existsById(id)) {
            throw new FindByIdException("No existe un paciente con el id ingresado");
        }
        logger.debug("Terminó la ejecución del método buscar paciente por ID");
        return new PacienteDto(pacienteRepository.getById(id));
    }

    @Override
    public PacienteDto buscarPorDni(String dni) {
        logger.debug("Iniciando método buscar paciente por dni");
        Optional<Paciente> paciente = pacienteRepository.findByDni(dni);
        if (paciente.isEmpty()) {
            throw new ServiceException("No existe un paciente con el dni ingresado");
        }
        logger.debug("Terminó la ejecución del método buscar paciente por dni");
        return new PacienteDto(paciente.get());
    }

    @Override
    public List<PacienteDto> buscarTodos() {
        logger.debug("Iniciando método buscar todos los pacientes");
        List<PacienteDto> pacientes = new ArrayList<>();
        for (Paciente p : pacienteRepository.findAll()) {
            pacientes.add(new PacienteDto(p));
        }
        logger.debug("Terminó la ejecución del método buscar a todos los pacientes");
        return pacientes;
    }

    @Override
    public String eliminar(Integer id) throws FindByIdException {
        logger.debug("Iniciando método eliminar paciente");
        if (!pacienteRepository.existsById(id)) {
            throw new FindByIdException("No existe un paciente con el id ingresado");
        }
        pacienteRepository.deleteById(id);
        logger.debug("Terminó la ejecución del método eliminar paciente");
        return "Paciente eliminado";
    }

    @Override
    public PacienteDto actualizar(PacienteDto paciente) throws FindByIdException {
        logger.debug("Iniciando método actualizar paciente");
        if (!pacienteRepository.existsById(paciente.getId())) {
            throw new FindByIdException("No existe un paciente con el id ingresado");
        }
        Paciente pacienteDB = pacienteRepository.findById(paciente.getId()).get();
        pacienteDB.setNombre(paciente.getNombre());
        pacienteDB.setApellido(paciente.getApellido());
        pacienteDB.setDni(paciente.getDni());
        pacienteDB.setFechaIngreso(paciente.getFechaIngreso());
        pacienteDB.setDomicilio(paciente.getDomicilio().toEntity());
        pacienteRepository.save(pacienteDB);
        logger.debug("Terminó la ejecución del método actualizar paciente");
        return new PacienteDto(pacienteDB);
    }
}
