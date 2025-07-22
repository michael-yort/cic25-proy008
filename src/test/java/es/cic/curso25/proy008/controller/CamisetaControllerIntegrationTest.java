package es.cic.curso25.proy008.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;


import es.cic.curso25.proy008.model.Camiseta;
import es.cic.curso25.proy008.repository.CamisetaRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CamisetaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired 
    private ObjectMapper objectMapper;

    @Autowired
    private CamisetaRepository camisetaRepository;

    @Test
    void testCreate() throws Exception {
        Camiseta camiseta = new Camiseta(39, "amarillo", "Nike", true);
        String json = objectMapper.writeValueAsString(camiseta);

        mockMvc.perform(post("/camiseta")
            .contentType("application/json")
            .content(json))
            .andExpect(status().isOk())
            .andExpect(result -> {
                Camiseta creada = objectMapper.readValue(result.getResponse().getContentAsString(), Camiseta.class);
                assertTrue(creada.getId() > 0);
                Optional<Camiseta> confirmada = camisetaRepository.findById(creada.getId());
                assertTrue(confirmada.isPresent());
            });
    }

    @Test
    void testGetById() throws Exception {
        Camiseta camiseta = new Camiseta(37, "blanco", "Adidas", false);
        camiseta = camisetaRepository.save(camiseta);

        mockMvc.perform(get("/camiseta/" + camiseta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"));
    }

    @Test
    void testUpdate() throws Exception {
        Camiseta camiseta = new Camiseta(36, "morado", "Nike", true);
        camiseta = camisetaRepository.save(camiseta);

        camiseta.setColor("rosa");
        String json = objectMapper.writeValueAsString(camiseta);

        mockMvc.perform(put("/camiseta/" + camiseta.getId())
            .contentType("application/json")
            .content(json))
            .andExpect(status().isOk())
            .andExpect(result -> {
                Camiseta actualizada = objectMapper.readValue(result.getResponse().getContentAsString(), Camiseta.class);
                assertTrue(actualizada.getColor().equals("rosa"));
            });
    }

    @Test
    void testDelete() throws Exception {
        Camiseta camiseta = new Camiseta(43, "naranja", "Adidas", false);
        camiseta = camisetaRepository.save(camiseta);

        mockMvc.perform(delete("/camiseta/" + camiseta.getId()))
            .andExpect(status().isOk());

        Optional<Camiseta> eliminada = camisetaRepository.findById(camiseta.getId());
        assertTrue(eliminada.isEmpty());
    }
}
