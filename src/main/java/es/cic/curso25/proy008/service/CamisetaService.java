package es.cic.curso25.proy008.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import es.cic.curso25.proy008.model.Camiseta;
import es.cic.curso25.proy008.repository.CamisetaRepository;


public class CamisetaService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CamisetaService.class);

        @Autowired
    private CamisetaRepository camisetaRepository;

    public Optional<Camiseta> get(long id) {
        LOGGER.info("Id camiseta " + id);
        Optional<Camiseta> camiseta = camisetaRepository.findById(id);

        return camiseta;
    }

    public List<Camiseta> get() {
        return camisetaRepository.findAll();
    }

    public Camiseta create(Camiseta camiseta) {

        camisetaRepository.save(camiseta);

        return camiseta;

    }

    public void delete(long id) {
        
        camisetaRepository.deleteById(id);

    }

    public Camiseta update(Camiseta camiseta) {

        camisetaRepository.save(camiseta);

        return camiseta;

    }
}
    
