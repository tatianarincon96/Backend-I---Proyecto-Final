package com.dh.clinica.service;
import com.dh.clinica.dto.responses.ResponseUserDTO;
import com.dh.clinica.exceptions.FindByIdException;
import com.dh.clinica.dto.UserDto;

import java.util.List;

public interface IUserService extends ClinicaService<ResponseUserDTO,UserDto> {
    ResponseUserDTO registrar(UserDto userDto);

    ResponseUserDTO buscar(Integer id) throws FindByIdException;

    List<ResponseUserDTO> buscarTodos();

    String eliminar(Integer id) throws FindByIdException ;

    ResponseUserDTO actualizar(UserDto userDto) throws FindByIdException ;
}
