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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController implements CRUDController<UserDto>  {

    @Autowired
    UserServiceImpl appUserServiceImpl;

    @GetMapping("/")
    public String home() {
        return "<h1> Bienvenid@ a la clínica odontológica </h1>";
    }

    @GetMapping("/403")
    public void forbidden() throws UnauthorizedAccessException {
        throw new UnauthorizedAccessException("No tiene permisos para acceder a este recurso", "User Role");
    }

    @Override
    @GetMapping("/todos")
    public ResponseEntity<?> buscarTodos() {
        List<ResponseUserDTO> usuarios = appUserServiceImpl.buscarTodos();
        if (usuarios.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron usuarios");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @PostMapping("/nuevo")
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
