package com.dh.clinica.service;
import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.FindByIdException;

import java.util.List;

public interface ITurnoService extends ClinicaService<TurnoDto> {

    TurnoDto registrar(TurnoDto turnoDto) throws BadRequestException, FindByIdException;

    TurnoDto buscar(Integer id) throws FindByIdException;

    List<TurnoDto> buscarTodos() throws FindByIdException;

    List<TurnoDto> buscarTurnosUltimaSemana() throws FindByIdException;

    String eliminar(Integer id) throws FindByIdException;

    TurnoDto actualizar(TurnoDto turnoDto) throws FindByIdException;
}
