package com.dh.clinica.service;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.exceptions.FindByIdException;

import java.rmi.server.ServerCloneException;
import java.util.List;

public interface IPacienteService extends ClinicaService<PacienteDto> {

    PacienteDto registrar(PacienteDto pacienteDto);

    PacienteDto buscar(Integer id) throws FindByIdException;

    PacienteDto buscarPorDni(String dni);

    List<PacienteDto> buscarTodos();

    String eliminar(Integer id) throws FindByIdException ;

    PacienteDto actualizar(PacienteDto pacienteDto) throws FindByIdException ;
}
