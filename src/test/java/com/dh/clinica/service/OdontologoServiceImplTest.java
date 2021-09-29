package com.dh.clinica.service;
import com.dh.clinica.controller.impl.PacienteController;
import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.exceptions.FindByIdException;
import com.dh.clinica.exceptions.ServiceException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

/*@FixMethodOrder(MethodSorters.NAME_ASCENDING)*/
@RunWith(SpringRunner.class)
@WebMvcTest(PacienteController.class)
/*@SpringBootTest*/
class OdontologoServiceImplTest {
   /* @Autowired
    private MockMvc mvc;
    @Autowired
    private OdontologoServiceImpl odontologoService;


    public void cargarDataSet() {
        this.odontologoService.registrar(new OdontologoDto("Santiago", "Paz", 3455647));
    }

    @org.junit.Test
    public void agregarOdontologo() {
        this.cargarDataSet();
        OdontologoDto odontologo = odontologoService.registrar(new OdontologoDto("Juan", "Ramirez", 348971960));
        Assert.assertTrue(odontologo.getId() != null);

    }

    @org.junit.Test
    public void eliminarOdontologoTest() throws ServiceException, FindByIdException {
        odontologoService.eliminar(1);
        Assert.assertTrue(odontologoService.buscar(1) != null);

    }

    @Test
    public void traerTodos() {
        List<OdontologoDto> odontologos = odontologoService.buscarTodos();
        Assert.assertTrue(!odontologos.isEmpty());
        Assert.assertTrue(odontologos.size() == 1);
        System.out.println(odontologos);
    }*/
}