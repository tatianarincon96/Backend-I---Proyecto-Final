package com.dh.clinica.service.impl;
import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.dto.responses.ResponseOdontologoDTO;
import com.dh.clinica.dto.responses.ResponsePacienteDTO;
import com.dh.clinica.dto.responses.ResponseTurnoDTO;
import com.dh.clinica.persistence.entity.Odontologo;
import com.dh.clinica.persistence.entity.Paciente;
import com.dh.clinica.persistence.entity.Turno;
import com.dh.clinica.persistence.repository.ITurnoRepository;
import com.dh.clinica.service.ITurnoService;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.FindByIdException;
import org.apache.coyote.Response;
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

    private ResponseTurnoDTO settearPacienteYOdontologo(Turno turnoEntity, ResponseTurnoDTO turnoDto) throws FindByIdException {
        PacienteDto paciente = pacienteServiceImpl.buscar(turnoEntity.getPaciente().getId());
        OdontologoDto odontologo = odontologoServiceImpl.buscar(turnoEntity.getOdontologo().getId());
        turnoDto.setPaciente(new ResponsePacienteDTO(paciente.getNombre(),paciente.getApellido()));
        turnoDto.setOdontologo(new ResponseOdontologoDTO(odontologo.getNombre(),odontologo.getApellido()));
        return turnoDto;
    }

    @Override
    public ResponseTurnoDTO registrar(TurnoDto turnoDto) throws BadRequestException, FindByIdException {
        logger.debug("Iniciando método registrar turno");
        PacienteDto paciente = pacienteServiceImpl.buscar(turnoDto.getPaciente().getId());
        OdontologoDto odontologo = odontologoServiceImpl.buscar(turnoDto.getOdontologo().getId());
        ResponseTurnoDTO response = null;
        if (verificarExistenciaPacienteYOdontologo(paciente.getId(), odontologo.getId()) && verificarFechaYHoraDisponible(turnoDto)) {
            Turno turnoEntity = turnoDto.toEntity(new Paciente(paciente.getId()), new Odontologo(odontologo.getId()), turnoDto.getFecha(), turnoDto.getHora());
            ResponseTurnoDTO turnoCreado = new ResponseTurnoDTO(turnoRepository.save(turnoEntity));
            turnoCreado.setPaciente(new ResponsePacienteDTO(paciente.getNombre(),paciente.getApellido()));
            turnoCreado.setOdontologo(new ResponseOdontologoDTO(odontologo.getNombre(),odontologo.getApellido()));
            response = turnoCreado;
        }
        logger.debug("Terminó la ejecución del método registrar turno");
        return response;
    }

    @Override
    public ResponseTurnoDTO buscar(Integer id) throws FindByIdException {
        logger.debug("Iniciando método buscar turno por ID");
        if (!turnoRepository.existsById(id)) {
            throw new FindByIdException("No existe un turno con el id ingresado");
        }
        Turno turno = turnoRepository.getById(id);
        ResponseTurnoDTO turnoDto = new ResponseTurnoDTO(turno);
        ResponseTurnoDTO turnoConPacienteYOdontologo = settearPacienteYOdontologo(turno,turnoDto);
        logger.debug("Terminó la ejecución del método buscar turno por ID");
        return turnoConPacienteYOdontologo;
    }

    @Override
    public List<ResponseTurnoDTO> buscarTodos() throws FindByIdException {
        logger.debug("Iniciando método buscar todos los turnos");
        List<ResponseTurnoDTO> turnos = new ArrayList<>();
        for (Turno turno : turnoRepository.findAll()) {
            ResponseTurnoDTO turnoDto = new ResponseTurnoDTO(turno);
            ResponseTurnoDTO turnoConPacienteYOdontologo = settearPacienteYOdontologo(turno,turnoDto);
            turnos.add(turnoConPacienteYOdontologo);
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
    public List<ResponseTurnoDTO> buscarTurnosProximaSemana() throws FindByIdException {
        List<Turno> turnosDB = turnoRepository.findAll();
        Stream<Turno> streamTurnos = turnosDB.stream().filter(t -> t.getFecha().isBefore(LocalDate.now().plusDays(8)) && t.getFecha().isAfter(LocalDate.now())); // Probar
        List<Turno> listTurnos = streamTurnos.collect(Collectors.toList());
        List<ResponseTurnoDTO> turnosProximaSemana = new ArrayList<>();
        for (Turno turno: listTurnos) {
            ResponseTurnoDTO turnoDto = new ResponseTurnoDTO(turno);
            ResponseTurnoDTO turnoConPacienteYOdontologo = settearPacienteYOdontologo(turno,turnoDto);
            turnosProximaSemana.add(turnoConPacienteYOdontologo);
        }
        return turnosProximaSemana;
    }

    @Override
    public ResponseTurnoDTO actualizar(TurnoDto turno) throws FindByIdException {
        logger.debug("Iniciando método actualizar turno");
        if (!turnoRepository.existsById(turno.getId()) || turno.getId() == null) {
            throw new FindByIdException("No existe un turno con el id ingresado");
        }
        Turno turnoEnDB = turnoRepository.findById(turno.getId()).get();
        turnoEnDB.setFecha(turno.getFecha());
        turnoEnDB.setHora(turno.getHora());
        ResponseTurnoDTO turnoDto = new ResponseTurnoDTO(turnoRepository.save(turnoEnDB));
        ResponseTurnoDTO turnoConPacienteYOdontologo = settearPacienteYOdontologo(turnoEnDB,turnoDto);
        logger.debug("Terminó la ejecución del método actualizar turno");
        return turnoConPacienteYOdontologo;
    }
}
