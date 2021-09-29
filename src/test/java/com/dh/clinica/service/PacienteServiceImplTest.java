package com.dh.clinica.service;
import com.dh.clinica.dto.DomicilioDto;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.exceptions.FindByIdException;
import com.dh.clinica.persistence.entity.Domicilio;
import com.dh.clinica.persistence.entity.Paciente;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
class PacienteServiceImplTest {
/*
    @Autowired
    private PacienteServiceImpl pacienteService;
    @Autowired
    private DomicilioServiceImpl domicilioService;

    public void cargarDatos() {
        DomicilioDto domicilio = new DomicilioDto("Av Santa fe", "444", "CABA", "Buenos Aires");
        PacienteDto p = pacienteService.registrar(new PacienteDto("Santiago", "Paz", "88888888", LocalDate.of(2018,3,5), domicilio));
        DomicilioDto domicilio1 = new DomicilioDto("Av Avellaneda", "333", "CABA", "Buenos Aires");
        PacienteDto p1 = pacienteService.registrar(new PacienteDto("Micaela", "Perez", "99999999", LocalDate.of(2018,3,5), domicilio1));
    }

    @Test
    public void deberiaTraerElPacienteBuscado() throws FindByIdException {
        //DADOS
        this.cargarDatos();
        DomicilioDto domicilio = new DomicilioDto("Calle", "123", "Temperley", "Buenos Aires");
        PacienteDto p = pacienteService.registrar(new PacienteDto("Tomas", "Pereyra", "12345678", LocalDate.of(2018,3,5), domicilio));

        //CUANDO
        Object respuesta = pacienteService.buscar(p.getId());

        //ENTONCES
        Assert.assertNotNull(respuesta);
    }

    @Test
    public void eliminarPacienteTest() throws FindByIdException {
        pacienteService.eliminar(3);
        Assert.assertTrue(pacienteService.buscar(3) != null);

    }

    @Test
    public void traerTodos() {
        List<PacienteDto> pacientes = pacienteService.buscarTodos();
        Assert.assertTrue(!pacientes.isEmpty());
        Assert.assertTrue(pacientes.size() == 2);
        System.out.println(pacientes);
    }*/
}