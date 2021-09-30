package com.dh.clinica.service;
import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.exceptions.FindByIdException;
import com.dh.clinica.persistence.repository.IOdontologoRepository;
import com.dh.clinica.service.impl.OdontologoServiceImpl;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class OdontologoServiceImplTest {
    @Mock
    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    @InjectMocks
    private IOdontologoService odontologoService = new OdontologoServiceImpl(odontologoRepository);
    private OdontologoDto odontologo;

    @BeforeEach
    public void setUp() {
        odontologo = new OdontologoDto();
        odontologo.setId(1);
        odontologo.setNombre("Santiago");
        odontologo.setApellido("Paz");
        odontologo.setMatricula(3455647);
    }

    @Test
    @Transactional
    public void testAgregarOdontologo() throws Exception {
        OdontologoDto o = odontologoService.registrar(odontologo);
        Assertions.assertNotNull(odontologoService.buscar(o.getId()));
    }

    @Test
    @Transactional
    public void eliminarOdontologoTest() throws FindByIdException {
        odontologoService.registrar(odontologo);
        odontologoService.eliminar(1);
        Assertions.assertEquals(0, odontologoService.buscarTodos().size());
    }

    @Test
    @Transactional
    public void traerTodos() {
        odontologoService.registrar(odontologo);
        List<OdontologoDto> odontologos = odontologoService.buscarTodos();
        Assertions.assertEquals(1, odontologos.size());
    }
}