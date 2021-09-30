package com.dh.clinica.controller.impl;
import com.dh.clinica.controller.CRUDController;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.dto.responses.ResponseTurnoDTO;
import com.dh.clinica.service.impl.PacienteServiceImpl;
import com.dh.clinica.service.impl.TurnoServiceImpl;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.FindByIdException;
import com.dh.clinica.exceptions.ServiceException;
import com.dh.clinica.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController implements CRUDController<TurnoDto> {

    @Autowired
    TurnoServiceImpl turnoServiceImpl;

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/todos")
    public ResponseEntity<?> buscarTodos() throws FindByIdException {
        List<ResponseTurnoDTO> turnos = turnoServiceImpl.buscarTodos();
        if (turnos.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron turnos");
        }
        return ResponseEntity.status(HttpStatus.OK).body(turnos);
    }

    @GetMapping("/ultimaSemana")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> buscarTodosUltimaSemana() throws FindByIdException {
        List<ResponseTurnoDTO> turnos = turnoServiceImpl.buscarTurnosUltimaSemana();
        if (turnos.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron turnos");
        }
        return ResponseEntity.status(HttpStatus.OK).body(turnos);
    }

    @Override
    @PostMapping("/nuevo")
    public ResponseEntity<?> crear(@RequestBody TurnoDto turno) throws ServiceException, BadRequestException, FindByIdException {
        ResponseTurnoDTO turnoNuevo = turnoServiceImpl.registrar(turno);
        if (turnoNuevo == null) {
            throw new ServiceException("No se pudo crear el turno", "crear turno");
        }
        return ResponseEntity.status(HttpStatus.OK).body(turnoNuevo);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) throws FindByIdException {
        return ResponseEntity.status(HttpStatus.OK).body(turnoServiceImpl.buscar(id));
    }

    @Override
    @PutMapping()
    public ResponseEntity<?> actualizar(@RequestBody TurnoDto turno) throws FindByIdException {
        return ResponseEntity.ok(turnoServiceImpl.actualizar(turno));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws FindByIdException {
        return ResponseEntity.status(HttpStatus.OK).body(turnoServiceImpl.eliminar(id));
    }
}

