package com.dh.clinica.controller;

import com.dh.clinica.controller.impl.PacienteController;
import com.dh.clinica.dto.DomicilioDto;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.exceptions.ServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PacientesIntegrationTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private PacienteController pacienteController;

    public void cargarDatos() throws ServiceException {
        DomicilioDto domicilio = new DomicilioDto("Av Santa fe", "444", "CABA", "Buenos Aires");
        pacienteController.crear(new PacienteDto("Santiago", "Paz", "88888888", LocalDate.of(2018, 3, 5), domicilio));
        DomicilioDto domicilio1 = new DomicilioDto("Av Avellaneda", "333", "CABA", "Buenos Aires");
        pacienteController.crear(new PacienteDto("Micaela", "Perez", "99999999", LocalDate.of(2018, 3, 5), domicilio1));
    }

    @Test
    public void buscarTodosLosPacientes() throws Exception {
        this.cargarDatos();
        MvcResult response = mvc.perform(MockMvcRequestBuilders.get("/pacientes/todos")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void buscarPacientePorId() throws Exception {
        this.cargarDatos();
        ResultActions response = mvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        Assert.assertNotNull(response);
    }

    @Test
    public void buscarPacientePorIdNoExistente() throws Exception {
        this.cargarDatos();
        mvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}", 10)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
