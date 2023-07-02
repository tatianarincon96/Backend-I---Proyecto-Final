package com.dh.clinica.controller.impl;

import com.dh.clinica.controller.CRUDController;
import com.dh.clinica.dto.responses.ResponseUserDTO;
import com.dh.clinica.exceptions.FindByIdException;
import com.dh.clinica.exceptions.ServiceException;
import com.dh.clinica.exceptions.UnauthorizedAccessException;
import com.dh.clinica.dto.UserDto;
import com.dh.clinica.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController implements CRUDController<UserDto>  {

    @Autowired
    UserServiceImpl appUserServiceImpl;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/post_odontologos.html")
    public String postOdontologo() {
        return "post_odontologos";
    }
    @GetMapping("/odontologoFunciones.html")
    public String funcionesOdontologo() {
        return "odontologoFunciones";
    }

    @GetMapping("/post_pacientes.html")
    public String postPaciente() {
        return "post_pacientes";
    }
    @GetMapping("/pacienteFunciones.html")
    public String funcionesPaciente() {
        return "pacienteFunciones";
    }

    @GetMapping("/post_turnos.html")
    public String postTurno() {
        return "post_turnos";
    }
    @GetMapping("/turnoFunciones.html")
    public String funcionesTurno() {
        return "turnoFunciones";
    }

    @GetMapping("/403")
    public void forbidden() throws UnauthorizedAccessException {
        throw new UnauthorizedAccessException("No tiene permisos para acceder a este recurso", "User Role");
    }

    @Override
    @GetMapping()
    public ResponseEntity<?> buscarTodos() {
        List<ResponseUserDTO> usuarios = appUserServiceImpl.buscarTodos();
        if (usuarios.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron usuarios");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @PostMapping()
    public ResponseEntity<?> crear(@RequestBody UserDto appUser) throws ServiceException {
        ResponseUserDTO nuevoUsuario;
        nuevoUsuario = appUserServiceImpl.registrar(appUser);
        if (nuevoUsuario == null) {
            throw new ServiceException("No se pudo registrar el usuario", "registrar usuario");
        }
        return ResponseEntity.status(HttpStatus.OK).body(nuevoUsuario);
    }

    @Override
    public ResponseEntity<?> buscar(Integer id) throws FindByIdException {
        return ResponseEntity.status(HttpStatus.OK).body(appUserServiceImpl.buscar(id));
    }

    @Override
    public ResponseEntity<?> actualizar(UserDto userDto) throws FindByIdException {
        return null;
    }

    @Override
    public ResponseEntity<String> eliminar(Integer id) throws FindByIdException {
        return null;
    }

}
