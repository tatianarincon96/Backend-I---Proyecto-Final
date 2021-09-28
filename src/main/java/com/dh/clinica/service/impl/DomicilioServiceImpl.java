package com.dh.clinica.service.impl;
import com.dh.clinica.dto.DomicilioDto;
import com.dh.clinica.persistence.entity.Domicilio;
import com.dh.clinica.persistence.repository.IODomicilioRepository;
import com.dh.clinica.service.IDomicilioService;
import com.dh.clinica.exceptions.FindByIdException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("DomicilioServiceImpl")
public class DomicilioServiceImpl implements IDomicilioService {

    private final Logger logger = Logger.getLogger(DomicilioServiceImpl.class);
    @Autowired
    private IODomicilioRepository domicilioRepository;

    public DomicilioServiceImpl() {}

    @Override
    public DomicilioDto registrar(DomicilioDto domicilio){
        logger.debug("Iniciando método registrar domicilio");
        Domicilio d = domicilioRepository.save(domicilio.toEntity());
        logger.debug("Terminó la ejecución del método registrar domicilio");
        return new DomicilioDto(d);
    }

    @Override
    public DomicilioDto buscar(Integer id) throws FindByIdException {
        logger.debug("Iniciando método buscar domicilio por ID");
        if (!domicilioRepository.existsById(id)) {
            throw new FindByIdException("No existe un domicilio con el id ingresado");
        }
        logger.debug("Terminó la ejecución del método buscar domicilio por ID");
        return new DomicilioDto(domicilioRepository.getById(id));
    }

    @Override
    public List<DomicilioDto> buscarTodos(){
        logger.debug("Iniciando método buscar todos los domicilios");
        List<DomicilioDto> domicilios = new ArrayList<>();
        for (Domicilio d : domicilioRepository.findAll()) {
            domicilios.add(new DomicilioDto(d));
        }
        logger.debug("Terminó la ejecución del método buscar a todos los domicilios");
        return domicilios;
    }

    @Override
    public String eliminar(Integer id) throws FindByIdException {
        logger.debug("Iniciando método eliminar domicilio");
        if (!domicilioRepository.existsById(id)) {
            throw new FindByIdException("No existe un domicilio con el id ingresado");
        }
        domicilioRepository.deleteById(id);
        logger.debug("Terminó la ejecución del método eliminar domicilio");
        return "Domicilio eliminado";
    }
    @Override
    public DomicilioDto actualizar(DomicilioDto domicilio) throws FindByIdException {
        logger.debug("Iniciando método actualizar domicilio");
        if (!domicilioRepository.existsById(domicilio.getId())) {
            throw new FindByIdException("No existe un domicilio con el id ingresado");
        }
        Domicilio domicilioEnDB = domicilioRepository.findById(domicilio.getId()).get();
        domicilioEnDB.setCalle(domicilio.getCalle());
        domicilioEnDB.setNumero(domicilio.getNumero());
        domicilioEnDB.setLocalidad(domicilio.getLocalidad());
        domicilioEnDB.setProvincia(domicilio.getProvincia());
        domicilioRepository.save(domicilioEnDB);
        logger.debug("Terminó la ejecución del método actualizar domicilio");
        return new DomicilioDto(domicilioEnDB);
    }

    public DomicilioDto buscarIdPorCalleYNumero(String calle, String numero) {
        logger.debug("Ejecutando método buscar domicilio por Calle y Número");
        Domicilio domicilio = domicilioRepository.findIdByStreetAndNumber(calle,numero);
        return domicilio != null ? new DomicilioDto(domicilio) : null;
    }
}
