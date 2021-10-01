package com.dh.clinica.service;
import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.dto.responses.ResponseTurnoDTO;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.FindByIdException;

import java.util.List;

public interface ITurnoService extends ClinicaService<ResponseTurnoDTO,TurnoDto> {

    ResponseTurnoDTO registrar(TurnoDto turnoDto) throws BadRequestException, FindByIdException;

    ResponseTurnoDTO buscar(Integer id) throws FindByIdException;

    List<ResponseTurnoDTO> buscarTodos() throws FindByIdException;

    List<ResponseTurnoDTO> buscarTurnosProximaSemana() throws FindByIdException;

    String eliminar(Integer id) throws FindByIdException;

    ResponseTurnoDTO actualizar(TurnoDto turnoDto) throws FindByIdException;
}
