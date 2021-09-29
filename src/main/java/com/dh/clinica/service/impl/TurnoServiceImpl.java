package com.dh.clinica.service.impl;
import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.persistence.entity.Odontologo;
import com.dh.clinica.persistence.entity.Paciente;
import com.dh.clinica.persistence.entity.Turno;
import com.dh.clinica.persistence.repository.ITurnoRepository;
import com.dh.clinica.service.ITurnoService;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.FindByIdException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Qualifier("TurnoServiceImpl")
public class TurnoServiceImpl implements ITurnoService {

    private final Logger logger = Logger.getLogger(TurnoServiceImpl.class);
    private final ITurnoRepository turnoRepository;
    private final PacienteServiceImpl pacienteServiceImpl;
    private final OdontologoServiceImpl odontologoServiceImpl;

    @Autowired
    public TurnoServiceImpl(ITurnoRepository turnoRepository, PacienteServiceImpl pacienteServiceImpl, OdontologoServiceImpl odontologoServiceImpl) {
        this.turnoRepository = turnoRepository;
        this.pacienteServiceImpl = pacienteServiceImpl;
        this.odontologoServiceImpl = odontologoServiceImpl;
    }

    private boolean verificarExistenciaPacienteYOdontologo(Integer pacienteId, Integer odontologoId) throws BadRequestException, FindByIdException {
        return pacienteServiceImpl.buscar(pacienteId) != null && odontologoServiceImpl.buscar(odontologoId) != null;
    }

    private boolean verificarFechaYHoraDisponible(TurnoDto turno) throws BadRequestException {
        List<Turno> turnos = turnoRepository.findAll();
        Turno turnoEntity = turno.toEntity(new Paciente(turno.getPaciente().getId()), new Odontologo(turno.getOdontologo().getId()), turno.getFecha(), turno.getHora());
        boolean fechaYHoraDisponibles = turnos.isEmpty() || turnos.stream().noneMatch(t -> t.equals(turnoEntity));
        if (!fechaYHoraDisponibles) {
            throw new BadRequestException("La hora y fecha solicitada no está disponible");
        }
        return true;
    }

    @Override
    public TurnoDto registrar(TurnoDto turnoDto) throws BadRequestException, FindByIdException {
        logger.debug("Iniciando método registrar turno");
        Integer pacienteId = turnoDto.getPaciente().getId();
        Integer odontologoId = turnoDto.getOdontologo().getId();
        TurnoDto response = null;
        if (verificarExistenciaPacienteYOdontologo(pacienteId, odontologoId) && verificarFechaYHoraDisponible(turnoDto)) {
            Turno turnoEntity = turnoDto.toEntity(new Paciente(pacienteId), new Odontologo(odontologoId), turnoDto.getFecha(), turnoDto.getHora());
            TurnoDto turnoCreado = new TurnoDto(turnoRepository.save(turnoEntity));
            turnoCreado.setPaciente(pacienteServiceImpl.buscar(pacienteId));
            turnoCreado.setOdontologo(odontologoServiceImpl.buscar(odontologoId));
            response = turnoCreado;
        }
        logger.debug("Terminó la ejecución del método registrar turno");
        return response;
    }

    @Override
    public TurnoDto buscar(Integer id) throws FindByIdException {
        logger.debug("Iniciando método buscar turno por ID");
        if (!turnoRepository.existsById(id)) {
            throw new FindByIdException("No existe un turno con el id ingresado");
        }
        Turno turno = turnoRepository.getById(id);
        TurnoDto turnoDto = new TurnoDto(turno);
        turnoDto.setPaciente(pacienteServiceImpl.buscar(turno.getPaciente().getId()));
        turnoDto.setOdontologo(odontologoServiceImpl.buscar(turno.getOdontologo().getId()));
        logger.debug("Terminó la ejecución del método buscar turno por ID");
        return turnoDto;
    }

    @Override
    public List<TurnoDto> buscarTodos() throws FindByIdException {
        logger.debug("Iniciando método buscar todos los turnos");
        List<TurnoDto> turnos = new ArrayList<>();
        for (Turno t : turnoRepository.findAll()) {
            TurnoDto turnoDto = new TurnoDto(t);
            turnoDto.setPaciente(pacienteServiceImpl.buscar(t.getPaciente().getId()));
            turnoDto.setOdontologo(odontologoServiceImpl.buscar(t.getOdontologo().getId()));
            turnos.add(turnoDto);
        }
        logger.debug("Terminó la ejecución del método buscar todos los turnos");
        return turnos;
    }

    @Override
    public String eliminar(Integer id) throws FindByIdException {
        logger.debug("Iniciando método eliminar turno");
        if (!turnoRepository.existsById(id)) {
            throw new FindByIdException("No existe un turno con el id ingresado");
        }
        turnoRepository.deleteById(id);
        logger.debug("Terminó la ejecución del método eliminar turno");
        return "Turno eliminado";
    }

    @Override
    public List<TurnoDto> buscarTurnosUltimaSemana() throws FindByIdException {
        List<Turno> turnosDB = turnoRepository.findAll();
        Stream<Turno> streamTurnos = turnosDB.stream().filter(t -> t.getFecha().isAfter(LocalDate.now().minusDays(7)));
        List<Turno> listTurnos = streamTurnos.collect(Collectors.toList());
        List<TurnoDto> turnosDtoUltimaSemana = new ArrayList<>();
        for (Turno turno: listTurnos) {
            TurnoDto turnoDto = new TurnoDto(turno);
            turnoDto.setPaciente(pacienteServiceImpl.buscar(turno.getPaciente().getId()));
            turnoDto.setOdontologo(odontologoServiceImpl.buscar(turno.getOdontologo().getId()));
            turnosDtoUltimaSemana.add(turnoDto);
        }
        return turnosDtoUltimaSemana;
    }

    @Override
    public TurnoDto actualizar(TurnoDto turno) throws FindByIdException {
        logger.debug("Iniciando método actualizar turno");
        if (!turnoRepository.existsById(turno.getId())) {
            throw new FindByIdException("No existe un turno con el id ingresado");
        }
        Turno turnoEnDB = turnoRepository.findById(turno.getId()).get();
/*        turnoEnDB.setPaciente(new Paciente(turno.getPaciente().getId()));
        turnoEnDB.setOdontologo(new Odontologo(turno.getOdontologo().getId()));*/
        turnoEnDB.setFecha(turno.getFecha());
        turnoEnDB.setHora(turno.getHora());
        turnoRepository.save(turnoEnDB);
        TurnoDto turnoDto = new TurnoDto(turnoEnDB);
        turnoDto.setPaciente(pacienteServiceImpl.buscar(turnoEnDB.getPaciente().getId()));
        turnoDto.setOdontologo(odontologoServiceImpl.buscar(turnoEnDB.getOdontologo().getId()));
        logger.debug("Terminó la ejecución del método actualizar turno");
        return turnoDto;
    }
}
