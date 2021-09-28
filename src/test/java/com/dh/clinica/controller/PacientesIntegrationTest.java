package com.dh.clinica.controller;
import com.dh.clinica.dto.DomicilioDto;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.service.impl.PacienteServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
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
    private PacienteServiceImpl pacienteService;

    public void cargarDatos() {
        DomicilioDto domicilio = new DomicilioDto("Av Santa fe", "444", "CABA", "Buenos Aires");
        PacienteDto p = pacienteService.registrar(new PacienteDto("Santiago", "Paz", "88888888", LocalDate.of(2018,3,5), domicilio));
        DomicilioDto domicilio1 = new DomicilioDto("Av Avellaneda", "333", "CABA", "Buenos Aires");
        PacienteDto p1 = pacienteService.registrar(new PacienteDto("Micaela", "Perez", "99999999", LocalDate.of(2018,3,5), domicilio1));
    }

    @Test
    public void buscarTodosLosPacientesAPI() throws Exception {
        this.cargarDatos();
        mvc.perform(MockMvcRequestBuilders.get("/pacientes/todos")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void buscarPacientePorId() throws Exception {
        this.cargarDatos();
        mvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void buscarPacientePorIdNoExistente() throws Exception {
        this.cargarDatos();
        mvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}",10)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
