package com.dh.clinica.service;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.FindByIdException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClinicaService<T,E> {

    T registrar(E e) throws BadRequestException, FindByIdException;

    T buscar(Integer id) throws FindByIdException;

    List<T> buscarTodos() throws FindByIdException;

    String eliminar(Integer id) throws FindByIdException;

    T actualizar(E e) throws FindByIdException;
}
