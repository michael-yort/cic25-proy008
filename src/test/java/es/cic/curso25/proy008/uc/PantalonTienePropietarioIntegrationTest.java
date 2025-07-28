package es.cic.curso25.proy008.uc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proy008.model.Pantalon;
import es.cic.curso25.proy008.model.Propietario;
import es.cic.curso25.proy008.service.PantalonService;
import jakarta.validation.constraints.AssertTrue;

@SpringBootTest
@AutoConfigureMockMvc
public class PantalonTienePropietarioIntegrationTest {

        private static final Logger LOGGER = LoggerFactory.getLogger(PantalonService.class);

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        public void crearPropietarioAlPantalon() throws Exception {

                /**
                 * Creo primero un propietario
                 */

                Propietario propietarioTest = new Propietario();

                propietarioTest.setNombre("Michael");
                propietarioTest.setTalla(38);
                propietarioTest.setPeso(64);

                // long idPropietario=propietarioTest.getId();
                // LOGGER.info("Propietario creado con id " + idPropietario);
                // Creo un pantalon
                Pantalon pantalonTest = new Pantalon("Levis", "azul", 38, true, propietarioTest);

                // convertimos el objeto de tipo asignarPropietarioAlPantalon en json con
                // ObjectMapper
                String pantalonACrearJson = objectMapper.writeValueAsString(pantalonTest);

                // con MockMvc simulamos la peticion HTTP para crear una pantalon y su
                // propietario
                MvcResult mvcResult = mockMvc.perform(post("/pantalon")
                                .contentType("application/json")
                                .content(pantalonACrearJson))
                                .andExpect(status().isOk())
                                .andExpect(personaResult -> {
                                        assertNotNull(
                                                        objectMapper.readValue(
                                                                        personaResult.getResponse()
                                                                                        .getContentAsString(),
                                                                        Pantalon.class),
                                                        "Al pantalon se le asignara un propietario");
                                })
                                .andReturn();

                Pantalon pantalonCreado = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                                Pantalon.class);
                Long id = pantalonCreado.getId();

                LOGGER.info("Pantalon con propietario creado correctamente: " + pantalonCreado.toString());
                LOGGER.info("Propietario creado con ID: " + pantalonCreado.getPropietario().toString());

                LOGGER.info("PRUEBO EL GET CON get(/pantalon/ + id)");

                mockMvc.perform(get("/pantalon/" + id))
                                .andDo(print())
                                .andExpect(status().isOk())
                                .andExpect(result -> {
                                        assertEquals(objectMapper.readValue(result.getResponse().getContentAsString(),
                                                        Pantalon.class).getId(),
                                                        id);
                                });

                pantalonCreado.getPropietario().setTalla(42);

                LOGGER.info("El GET me devuelve este pantalon: " + pantalonCreado.toString() + "\n y este propietario: "
                                + pantalonCreado.getPropietario().toString());

                String pantalonJson = objectMapper.writeValueAsString(pantalonCreado);

                mockMvc.perform(put("/pantalon/{id}", pantalonCreado.getId()) // <-- ID insertado
                                .contentType("application/json")
                                .content(pantalonJson))
                                .andDo(print())
                                .andExpect(status().isOk());

                LOGGER.info("Pruebo el PUT cambiando la talla: " + pantalonCreado.getPropietario().getTalla());

                assertEquals(42, pantalonCreado.getPropietario().getTalla());

                LOGGER.info("Pruebo el DELETE del pantalón con ID 1");

                // Eliminar el pantalón
                mockMvc.perform(delete("/pantalon/{id}", pantalonCreado.getId()))
                                .andDo(print())
                                .andExpect(status().isOk());

                // Intentar obtenerlo después de borrado → debe dar 404
                mockMvc.perform(get("/pantalon/{id}", pantalonCreado.getId()))
                                .andDo(print())
                                .andExpect(status().isNotFound());

                LOGGER.info("Verificado que el pantalón ha sido eliminado correctamente");
        }

}
