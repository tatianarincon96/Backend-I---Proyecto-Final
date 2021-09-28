package com.dh.clinica.service;
import com.dh.clinica.dto.DomicilioDto;
import com.dh.clinica.exceptions.FindByIdException;

import java.util.List;

public interface IDomicilioService extends ClinicaService<DomicilioDto> {

    DomicilioDto registrar(DomicilioDto domicilioDto);

    DomicilioDto buscar(Integer id) throws FindByIdException;

    List<DomicilioDto> buscarTodos();

    String eliminar(Integer id) throws FindByIdException;

    DomicilioDto actualizar(DomicilioDto domicilioDto) throws FindByIdException;
}
