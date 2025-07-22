package es.cic.curso25.proy008.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso25.proy008.exception.ModificationSecurityException;
import es.cic.curso25.proy008.model.Camiseta;
import es.cic.curso25.proy008.repository.CamisetaRepository;

@Service
public class CamisetaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CamisetaService.class);

    @Autowired
    private CamisetaRepository camisetaRepository;

    public List<Camiseta> getAll() {
        return camisetaRepository.findAll();
    }

    public Optional<Camiseta> getById(long id) {
        LOGGER.info("Buscando camiseta con id: " + id);
        return camisetaRepository.findById(id);
    }

    public Camiseta create(Camiseta camiseta) {
        if (camiseta.getId() != 0) {
            throw new ModificationSecurityException("No se debe incluir un ID al crear una camiseta.");
        }
        return camisetaRepository.save(camiseta);
    }

    public Camiseta update(Camiseta camiseta) {
        if (!camisetaRepository.existsById(camiseta.getId())) {
            throw new RuntimeException("No existe camiseta con id: " + camiseta.getId());
        }
        return camisetaRepository.save(camiseta);
    }

    public void delete(long id) {
        camisetaRepository.deleteById(id);
    }

    public void deleteAll() {
        camisetaRepository.deleteAll();
    }
}
