package com.dh.clinica.service.impl;
import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.persistence.entity.Odontologo;
import com.dh.clinica.persistence.repository.IOdontologoRepository;
import com.dh.clinica.service.IOdontologoService;
import com.dh.clinica.exceptions.FindByIdException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("OdontologoServiceImpl")
public class OdontologoServiceImpl implements IOdontologoService {

    private final Logger logger = Logger.getLogger(OdontologoServiceImpl.class);
    @Autowired
    private IOdontologoRepository odontologoRepository;

    public OdontologoServiceImpl() {
    }

    public OdontologoServiceImpl(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public OdontologoDto registrar(OdontologoDto odontologo) {
        logger.debug("Iniciando método registrar odontólogo");
        Odontologo o = odontologoRepository.save(odontologo.toEntity());
        logger.debug("Terminó la ejecución del método registrar odontólogo");
        return new OdontologoDto(o);
    }

    @Override
    public OdontologoDto buscar(Integer id) throws FindByIdException {
        logger.debug("Iniciando método buscar odontólogo por ID");
        if (!odontologoRepository.existsById(id)) {
            throw new FindByIdException("No existe un odontólogo con el id ingresado");
        }
        logger.debug("Terminó la ejecución del método buscar odontólogo por ID");
        return new OdontologoDto(odontologoRepository.getById(id));
    }

    @Override
    public List<OdontologoDto> buscarTodos() {
        logger.debug("Iniciando método buscar todos los odontólogos");
        List<OdontologoDto> odontologo = new ArrayList<>();
        for (Odontologo o : odontologoRepository.findAll()) {
            odontologo.add(new OdontologoDto(o));
        }
        logger.debug("Terminó la ejecución del método buscar todos los odontólogos");
        return odontologo;
    }

    @Override
    public String eliminar(Integer id) throws FindByIdException {
        logger.debug("Iniciando método eliminar odontólogo");
        if (!odontologoRepository.existsById(id)) {
            throw new FindByIdException("No existe un odontólogo con el id ingresado");
        }
        odontologoRepository.deleteById(id);
        logger.debug("Terminó la ejecución del método eliminar odontólogo");
        return "Odontólogo eliminado";
    }

    @Override
    public OdontologoDto actualizar(OdontologoDto odontologo) throws FindByIdException {
        logger.debug("Iniciando método actualizar odontólogo");
        if (!odontologoRepository.existsById(odontologo.getId())) {
            throw new FindByIdException("No existe un odontólogo con el id ingresado");
        }
        Odontologo odontologoEnDB = odontologoRepository.findById(odontologo.getId()).get();
        odontologoEnDB.setNombre(odontologo.getNombre());
        odontologoEnDB.setApellido(odontologo.getApellido());
        odontologoEnDB.setMatricula(odontologo.getMatricula());
        odontologoRepository.save(odontologoEnDB);
        logger.debug("Terminó la ejecución del método actualizar odontólogo");
        return new OdontologoDto(odontologoEnDB);
    }
}
