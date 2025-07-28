package es.cic.curso25.proy008.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proy008.model.Pantalon;
import es.cic.curso25.proy008.repository.PantalonRepository;
import es.cic.curso25.proy008.service.PantalonService;

@SpringBootTest
@AutoConfigureMockMvc
public class PantalonControllerIntegrationTest {


        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private PantalonRepository pantalonRepository;

        @Test
        void testCreate() throws Exception {

                Pantalon pantalon = new Pantalon("Lewis", "Azul", 32, true, null);
                // pantalon.setPropietario(null);

                String pantalonJson = objectMapper.writeValueAsString(pantalon);

                mockMvc.perform(post("/pantalon")
                                .contentType("application/json")
                                .content(pantalonJson))
                                .andExpect(status().isOk())
                                .andExpect(result -> {
                                        String respuesta = result.getResponse().getContentAsString();
                                        Pantalon pantalonCreado = objectMapper.readValue(respuesta, Pantalon.class);

                                        assertTrue(pantalonCreado.getId() >= 1, "El valor debe ser mayor que 0");

                                        Optional<Pantalon> pantalonRealmenteCreado = pantalonRepository
                                                        .findById(pantalonCreado.getId());
                                        assertTrue(pantalonRealmenteCreado.isPresent());

                                });

        }

        @Test
        void testDelete() throws Exception {
                Pantalon pantalon = new Pantalon("Lewis", "Azul", 32, true, null);
                Pantalon pantalonGuardado = pantalonRepository.save(pantalon);
                Long id = pantalonGuardado.getId();

                mockMvc.perform(delete("/pantalon/" + id))
                                .andExpect(status().isOk());

                Optional<Pantalon> pantalonEliminado = pantalonRepository.findById(pantalon.getId());

                assertTrue(pantalonEliminado.isEmpty());

        }

        @Test
        void testGetAll() throws Exception {
                // Paso 1: Crear pantalones de prueba
                Pantalon pantalon1 = new Pantalon("Lewis", "Azul", 32, true, null);
                Pantalon pantalon2 = new Pantalon("Nike", "Negro", 30, false, null);

                // Paso 2: Guardarlos en la base de datos
                pantalonRepository.deleteAll();
                pantalonRepository.save(pantalon1);
                pantalonRepository.save(pantalon2);

                // Paso 3: Hacer la petición GET a /pantalon
                MvcResult resultado = mockMvc.perform(get("/pantalon"))
                                .andExpect(status().isOk())
                                .andReturn();

                // Paso 4: Obtener el contenido JSON de la respuesta como texto
                String contenidoJson = resultado.getResponse().getContentAsString();

                // Paso 5: Convertir el JSON en una lista de objetos Pantalon
                ObjectMapper objectMapper = new ObjectMapper();
                List<Pantalon> lista = objectMapper.readValue(
                                contenidoJson,
                                new TypeReference<List<Pantalon>>() {
                                });

                // Paso 6: Verificar que se devolvieron 2 pantalones
                assertEquals(2, lista.size());

                // Paso 7: Verificar que los datos son los esperados
                assertEquals("Lewis", lista.get(0).getMarca());
                assertEquals("Nike", lista.get(1).getMarca());
        }

        @Test
        void testGet2() throws Exception {

                // Paso 1: Crear pantalones de prueba
                Pantalon pantalon1 = new Pantalon("Lewis", "Azul", 32, true, null);
                Pantalon pantalon2 = new Pantalon("Nike", "Negro", 30, false, null);
                Pantalon pantalon3 = new Pantalon("Jeans", "Gris", 38, true, null);

                // Paso 2: Guardarlos en la base de datos
                pantalonRepository.deleteAll();
                pantalonRepository.save(pantalon1);
                pantalonRepository.save(pantalon2);
                pantalonRepository.save(pantalon3);

                // Paso 3: Hacer la petición GET a /pantalon y ver los valores obtenidos por
                // DebugConsole con .andDo(print())
                MvcResult resultado = mockMvc.perform(get("/pantalon/2")).andDo(print())
                                .andExpect(status().isOk())
                                .andReturn();

                // Paso 4: Obtener el contenido JSON de la respuesta como texto
                String contenidoJson = resultado.getResponse().getContentAsString();

                // Paso 5: Convertir el JSON en una lista de objetos Pantalon
                ObjectMapper objectMapper = new ObjectMapper();
                Pantalon pantalonBuscado = objectMapper.readValue(
                                contenidoJson,
                                new TypeReference<Pantalon>() {
                                });

                // Paso 6: Verificar que se devolvieron 2 pantalones
                assertEquals(2, pantalonBuscado.getId());

        }

        @Test
        void testUpdate() throws Exception {

                // 1. Crear un hábito
                Pantalon pantalon1 = new Pantalon("Lewis", "Azul", 32, true, null);

                String habitoJson = objectMapper.writeValueAsString(pantalon1);
                String respuestaCreacion = mockMvc.perform(post("/pantalon")
                                .contentType("application/json")
                                .content(habitoJson))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse()
                                .getContentAsString();

                Pantalon pantalonCreado = objectMapper.readValue(respuestaCreacion, Pantalon.class);
                Long idCreado = pantalonCreado.getId();

                // 2. Modificar el hábito
                pantalonCreado.setColor("Amarillo");
                pantalonCreado.setTalla(40);

                String habitoActualizadoJson = objectMapper.writeValueAsString(pantalonCreado);

                // 3. Hacer PUT con el ID en la URL
                mockMvc.perform(put("/pantalon/" + idCreado)
                                .contentType("application/json")
                                .content(habitoActualizadoJson))
                                .andExpect(status().isOk())
                                .andExpect(result -> {
                                        String respuesta = result.getResponse().getContentAsString();
                                        Pantalon actualizado = objectMapper.readValue(respuesta, Pantalon.class);

                                        // Verificar los cambios
                                        assertEquals("Amarillo", actualizado.getColor());
                                        assertEquals(40, actualizado.getTalla());
                                        assertEquals(idCreado, actualizado.getId());
                                });

        }
}
