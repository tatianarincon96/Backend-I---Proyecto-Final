package com.dh.clinica.controller.impl;
import com.dh.clinica.controller.CRUDController;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.service.impl.PacienteServiceImpl;
import com.dh.clinica.exceptions.FindByIdException;
import com.dh.clinica.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController implements CRUDController<PacienteDto> {

    @Autowired
    PacienteServiceImpl pacienteServiceImpl;

    @Override
    @GetMapping()
    public ResponseEntity<?> buscarTodos() {
        List<PacienteDto> pacientes = pacienteServiceImpl.buscarTodos();
        if (pacientes.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron pacientes");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pacientes);
    }

    @Override
    @PostMapping()
    public ResponseEntity<?> crear(@RequestBody PacienteDto paciente) throws ServiceException {
        PacienteDto pacienteNuevo = pacienteServiceImpl.registrar(paciente);
        if (pacienteNuevo == null) {
            throw new ServiceException("No se pudo registrar el paciente", "registrar paciente");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pacienteNuevo);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) throws FindByIdException {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteServiceImpl.buscar(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> buscar(@PathVariable String email) throws FindByIdException {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteServiceImpl.buscarPorEmail(email));
    }

    @Override
    @PutMapping()
    public ResponseEntity<?> actualizar(@RequestBody PacienteDto paciente) throws FindByIdException {
        return ResponseEntity.ok(pacienteServiceImpl.actualizar(paciente));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws FindByIdException {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteServiceImpl.eliminar(id));
    }
}
