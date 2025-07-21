package es.cic.curso25.proy008.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso25.proy008.repository.PantalonRepository;

@Service
public class PantalonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PantalonService.class);

    @Autowired
    private PantalonRepository habitoRepository;
}
