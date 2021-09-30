package com.dh.clinica.service;
import com.dh.clinica.dto.DomicilioDto;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.exceptions.FindByIdException;
import com.dh.clinica.persistence.repository.IPacienteRepository;
import com.dh.clinica.service.impl.PacienteServiceImpl;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
class PacienteServiceImplTest {
    @Mock
    @Autowired
    private IPacienteRepository pacienteRepository;

    @Autowired
    @InjectMocks
    private IPacienteService pacienteService = new PacienteServiceImpl(pacienteRepository);
    private PacienteDto paciente;

    @BeforeEach
    public void setUp() {
        paciente = new PacienteDto();
        paciente.setId(1);
        paciente.setNombre("Santiago");
        paciente.setApellido("Paz");
        paciente.setDni("12345261");
        paciente.setFechaIngreso(LocalDate.of(2018,03,05));
        paciente.setDomicilio(new DomicilioDto("Las Heras","1351","Banfield","Buenos Aires"));
    }

    @Test
    @Transactional
    public void testAgregarPaciente() throws Exception {
        PacienteDto p = pacienteService.registrar(paciente);
        Assertions.assertNotNull(pacienteService.buscar(p.getId()));
    }

    @Test
    @Transactional
    public void eliminarPacienteTest() throws FindByIdException {
        pacienteService.registrar(paciente);
        pacienteService.eliminar(1);
        Assertions.assertEquals(0, pacienteService.buscarTodos().size());
    }

    @Test
    @Transactional
    public void traerTodos() {
        pacienteService.registrar(paciente);
        List<PacienteDto> pacientes = pacienteService.buscarTodos();
        Assertions.assertEquals(1, pacientes.size());
    }
}