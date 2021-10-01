package com.dh.clinica.service;
import com.dh.clinica.controller.impl.PacienteController;
import com.dh.clinica.dto.DomicilioDto;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.FindByIdException;
import com.dh.clinica.exceptions.GlobalException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDate;

import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(PacienteController.class)
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class PacienteServiceUnitTest {
    private MockMvc mockMvc;
    @Mock
    private IPacienteService pacienteService;

    @InjectMocks
    private PacienteController pacienteController;

    private PacienteDto paciente;
    private PacienteDto pacienteInexistente;
    private PacienteDto pacienteExistente;
    private DomicilioDto domicilio;

    @Before
    public void reset() throws BadRequestException, FindByIdException {
        mockMvc = MockMvcBuilders.standaloneSetup(pacienteController).setControllerAdvice(GlobalException.class).build();
        domicilio = new DomicilioDto("Las Heras","1351","Banfield","Buenos Aires");
        paciente = new PacienteDto("Martin", "Gercia", "12864378", LocalDate.now(),domicilio);
        pacienteInexistente = new PacienteDto("Martin", "Gercia", "nn", LocalDate.now(),domicilio);
        pacienteExistente = new PacienteDto("Martin", "Gercia", "38763098", LocalDate.now(),domicilio);
        pacienteExistente.setId(1);
        pacienteInexistente.setId(999);
        configureMockito();
    }

    public void configureMockito() throws BadRequestException, FindByIdException {
        Mockito.when(pacienteService.registrar(paciente)).thenReturn(pacienteExistente);
        Mockito.when(pacienteService.buscar(1)).thenReturn(pacienteExistente);
        Mockito.when(pacienteService.buscar(999)).thenThrow(new FindByIdException("No existe ningún paciente con el id ingresado"));
        Mockito.when(pacienteService.actualizar(pacienteInexistente)).thenThrow(new FindByIdException("No existe ningún paciente con el id ingresado"));
        Mockito.when(pacienteService.actualizar(pacienteExistente)).thenReturn(pacienteExistente);
        doThrow(new FindByIdException("No existe ningún paciente con el id ingresado")).when(pacienteService).eliminar(10);
    }

    @org.junit.Test
    public void testAgregarPaciente() throws Exception {
        PacienteDto o = pacienteService.registrar(paciente);
        Assertions.assertEquals(pacienteExistente,o);
    }

    @org.junit.Test
    public void eliminarPacientePorId() throws FindByIdException {
        Assertions.assertThrows(FindByIdException.class, () -> pacienteService.eliminar(10));
    }

    @org.junit.Test
    public void buscarPacientePorIdExistente() throws FindByIdException {
        Assertions.assertEquals(pacienteExistente,pacienteService.buscar(1));
    }

    @org.junit.Test
    public void buscarPacientePorIdInexistente() throws FindByIdException {
        Assertions.assertThrows(FindByIdException.class, () -> pacienteService.buscar(999));
    }

    @org.junit.Test
    public void actualizarPacienteExistente() throws FindByIdException {
        Assertions.assertEquals(pacienteExistente,pacienteService.actualizar(pacienteExistente));
    }

    @Test
    public void actualizarPacienteInexistente() throws FindByIdException {
        Assertions.assertThrows(FindByIdException.class, () -> pacienteService.actualizar(pacienteInexistente));
    }
}