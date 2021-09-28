package com.dh.clinica.service;
import com.dh.clinica.exceptions.FindByIdException;
import com.dh.clinica.dto.UserDto;

import java.util.List;

public interface IUserService extends ClinicaService<UserDto> {
    UserDto registrar(UserDto userDto);

    UserDto buscar(Integer id) throws FindByIdException;

    List<UserDto> buscarTodos();

    String eliminar(Integer id) throws FindByIdException ;

    UserDto actualizar(UserDto userDto) throws FindByIdException ;
}
