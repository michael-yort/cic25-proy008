package es.cic.curso25.proy008.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso25.proy008.model.Propietario;
import es.cic.curso25.proy008.repository.PropietarioRepository;

@Service
@Transactional
public class PropietarioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropietarioService.class);

    @Autowired
    private PropietarioRepository propietarioRepository;

    public Optional<Propietario> get(Long id) {
        LOGGER.info(String.format("Buscando la Propietario con id %d", id));
        return propietarioRepository.findById(id);
    }

    public List<Propietario> get() {
    LOGGER.info("Buscando todas las Propietarios");
    return propietarioRepository.findAll();
    }

    public Propietario create(Propietario propietario){
    LOGGER.info("Creando una Propietario");
    return propietarioRepository.save(propietario);
    }

    public Propietario update(Propietario propietario){
    LOGGER.info(String.format("Actualizando el Propietario con id %d",propietario.getId()));
    return propietarioRepository.save(propietario);
    }

    public void delete(Long id){
    propietarioRepository.deleteById(id);
    }
}
