package com.dh.clinica.service;
import com.dh.clinica.controller.impl.OdontologoController;
import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.FindByIdException;
import com.dh.clinica.exceptions.GlobalException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(OdontologoController.class)
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class OdontologoServiceUnitTest {
    private MockMvc mockMvc;
    @Mock
    private IOdontologoService odontologoService;

    @InjectMocks
    private OdontologoController odontologoController;

    private OdontologoDto odontologo;
    private OdontologoDto odontologoInexistente;
    private OdontologoDto odontologoExistente;

    @Before
    public void reset() throws BadRequestException, FindByIdException {
        mockMvc = MockMvcBuilders.standaloneSetup(odontologoController).setControllerAdvice(GlobalException.class).build();
        odontologo = new OdontologoDto(123456789, "Jorge", "Lopez", 123456);
        odontologoInexistente = new OdontologoDto(123456789, "Jorge", "Lopez", 123456);
        odontologoExistente = new OdontologoDto(123456789, "Jorge", "Lopez", 123456);
        odontologoExistente.setId(1);
        odontologoInexistente.setId(999);
        configureMockito();
    }

    public void configureMockito() throws BadRequestException, FindByIdException {
        Mockito.when(odontologoService.registrar(odontologo)).thenReturn(odontologoExistente);
        Mockito.when(odontologoService.buscar(1)).thenReturn(odontologoExistente);
        Mockito.when(odontologoService.buscar(999)).thenThrow(new FindByIdException("No existe ningún odontólogo con el id ingresado"));
        Mockito.when(odontologoService.actualizar(odontologoInexistente)).thenThrow(new FindByIdException("No existe ningún odontólogo con el id ingresado"));
        Mockito.when(odontologoService.actualizar(odontologoExistente)).thenReturn(odontologoExistente);
        doThrow(new FindByIdException("No existe ningún odontólogo con el id ingresado")).when(odontologoService).eliminar(10);
    }

    @Test
    public void testAgregarOdontologo() throws Exception {
        OdontologoDto o = odontologoService.registrar(odontologo);
        Assertions.assertEquals(odontologoExistente,o);
    }

    @Test
    public void eliminarOdontologoPorId() throws FindByIdException {
        Assertions.assertThrows(FindByIdException.class, () -> odontologoService.eliminar(10));
    }

    @Test
    public void buscarOdontologoPorIdExistente() throws FindByIdException {
        Assertions.assertEquals(odontologoExistente,odontologoService.buscar(1));
    }

    @Test
    public void buscarOdontologoPorIdInexistente() throws FindByIdException {
        Assertions.assertThrows(FindByIdException.class, () -> odontologoService.buscar(999));
    }

    @Test
    public void actualizarOdontologoExistente() throws FindByIdException {
        Assertions.assertEquals(odontologoExistente,odontologoService.actualizar(odontologoExistente));
    }

    @Test
    public void actualizarOdontologoInexistente() throws FindByIdException {
        Assertions.assertThrows(FindByIdException.class, () -> odontologoService.actualizar(odontologoInexistente));
    }

}