package es.cic.curso25.proy008.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso25.proy008.exception.ModificationSecurityException;
import es.cic.curso25.proy008.model.Pantalon;
import es.cic.curso25.proy008.repository.PantalonRepository;

@Service
public class PantalonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PantalonService.class);

    @Autowired
    private PantalonRepository pantalonRepository;

    public List<Pantalon> get() {
        return pantalonRepository.findAll();
    }

    public Optional<Pantalon> get(Long id) {
        return pantalonRepository.findById(id);
    }

    public Pantalon create(Pantalon pantalon) {
        if (pantalon.getId()!=null) {
            throw new ModificationSecurityException("No se debe incluir un id al crear un pantalon");
        }
        LOGGER.info("Pantalon creado correctamente");
        return pantalonRepository.save(pantalon);
    }

    public Pantalon update(Pantalon pantalon) {
        if (pantalon.getId() == null || !pantalonRepository.existsById(pantalon.getId())) {
            throw new IllegalArgumentException("El pantalón no existe o no tiene ID.");
        }
        return pantalonRepository.save(pantalon);
    }

    public void delete(Long id) {
        if (!pantalonRepository.existsById(id)) {
            throw new IllegalArgumentException("El pantalón no existe.");
        }
        LOGGER.info("Borrando pantalon con id "+ id);
        pantalonRepository.deleteById(id);
    }

     public void deleteAll() {
        pantalonRepository.deleteAll();
    }

}
