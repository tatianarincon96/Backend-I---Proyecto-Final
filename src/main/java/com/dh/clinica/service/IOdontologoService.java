package com.dh.clinica.service;
import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.exceptions.FindByIdException;

import java.util.List;

public interface IOdontologoService extends ClinicaService<OdontologoDto,OdontologoDto> {

    OdontologoDto registrar(OdontologoDto odontologoDto);

    OdontologoDto buscar(Integer id) throws FindByIdException;

    List<OdontologoDto> buscarTodos();

    String eliminar(Integer id) throws FindByIdException;

    OdontologoDto actualizar(OdontologoDto odontologoDto) throws FindByIdException;
}
