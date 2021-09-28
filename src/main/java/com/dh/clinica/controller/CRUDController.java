package com.dh.clinica.controller;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.FindByIdException;
import com.dh.clinica.exceptions.UnauthorizedAccessException;
import com.dh.clinica.exceptions.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface CRUDController<T> {

    ResponseEntity<?> buscarTodos() throws FindByIdException, UnauthorizedAccessException;

    ResponseEntity<?> crear(@RequestBody T t) throws ServiceException, BadRequestException, FindByIdException, UnauthorizedAccessException;

    ResponseEntity<?> buscar(@PathVariable Integer id) throws FindByIdException, UnauthorizedAccessException;

    ResponseEntity<?> actualizar(@RequestBody T t) throws FindByIdException, UnauthorizedAccessException;

    ResponseEntity<String> eliminar(@PathVariable Integer id) throws FindByIdException, UnauthorizedAccessException;
}
