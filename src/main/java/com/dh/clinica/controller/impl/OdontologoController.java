package com.dh.clinica.controller.impl;
import com.dh.clinica.controller.CRUDController;
import com.dh.clinica.exceptions.FindByIdException;
import com.dh.clinica.exceptions.UnauthorizedAccessException;
import com.dh.clinica.exceptions.ServiceException;
import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.service.impl.OdontologoServiceImpl;
import com.dh.clinica.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController implements CRUDController<OdontologoDto> {
    private final Logger logger = Logger.getLogger(OdontologoController.class);

    @Autowired
    UserServiceImpl appUserServiceImpl;
    @Autowired
    private OdontologoServiceImpl odontologoServiceImpl;

    @Override
    @GetMapping("/todos")
    public ResponseEntity<?> buscarTodos() throws UnauthorizedAccessException {
        List<OdontologoDto> odontologos = odontologoServiceImpl.buscarTodos();
        if (odontologos.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron odontólogos");
        }
        return ResponseEntity.status(HttpStatus.OK).body(odontologos);
    }

    @Override
    @PostMapping()
    public ResponseEntity<?> crear(@RequestBody OdontologoDto odontologo) throws ServiceException, UnauthorizedAccessException {
        OdontologoDto odontologoNuevo;
        odontologoNuevo = odontologoServiceImpl.registrar(odontologo);
        if (odontologoNuevo == null) {
            throw new ServiceException("No se pudo registrar el odontólogo", "registrar odontólogo");
        }
        return ResponseEntity.status(HttpStatus.OK).body(odontologoNuevo);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) throws FindByIdException, UnauthorizedAccessException {
        return ResponseEntity.status(HttpStatus.OK).body(odontologoServiceImpl.buscar(id));
    }

    @Override
    @PutMapping()
    public ResponseEntity<?> actualizar(@RequestBody OdontologoDto odontologo) throws FindByIdException, UnauthorizedAccessException {
        return ResponseEntity.status(HttpStatus.OK).body(odontologoServiceImpl.actualizar(odontologo));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws FindByIdException, UnauthorizedAccessException {
        return ResponseEntity.status(HttpStatus.OK).body(odontologoServiceImpl.eliminar(id));
    }
}
