package com.dh.clinica.controller;
import com.dh.clinica.controller.impl.OdontologoController;
import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.exceptions.ServiceException;
import com.dh.clinica.exceptions.UnauthorizedAccessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OdontologosIntegrationTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private OdontologoController odontologoController;

    public void cargarDatos() throws ServiceException, UnauthorizedAccessException {
        odontologoController.crear(new OdontologoDto("Julia", "Garcia", 122453));
        odontologoController.crear(new OdontologoDto("Marcos", "Lopez", 391253));
    }

    @Test
    public void crearOdontologo() throws Exception {
        OdontologoDto payloadDTO = new OdontologoDto("Julia", "Garcia", 122453);
        OdontologoDto responseDTO = new OdontologoDto(1, "Julia", "Garcia", 122453);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String odontologoEntity = writer.writeValueAsString(payloadDTO);
        String odontologoExpectedResponse = writer.writeValueAsString(responseDTO);

        MvcResult response = mvc.perform(MockMvcRequestBuilders.post("/odontologos/nuevo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(odontologoEntity))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andReturn();
        Assert.assertEquals(odontologoExpectedResponse, response.getResponse().getContentAsString());
    }

    @Test
    public void buscarTodosLosOdontologos() throws Exception {
        this.cargarDatos();
        MvcResult response = mvc.perform(MockMvcRequestBuilders.get("/odontologos/todos")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void buscarPacientePorId() throws Exception {
        this.cargarDatos();
        ResultActions response = mvc.perform(MockMvcRequestBuilders.get("/odontologos/{id}", 2)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        Assert.assertNotNull(response);
    }

    @Test
    public void buscarPacientePorIdNoExistente() throws Exception {
        this.cargarDatos();
        mvc.perform(MockMvcRequestBuilders.get("/odontologos/{id}", 10)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
