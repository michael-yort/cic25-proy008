package es.cic.curso25.proy008.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.cic.curso25.proy008.model.Pantalon;

@SpringBootTest
public class PantalonServiceIntegrationTest {

    @Autowired
    private PantalonService pantalonService;

    @Test
    void testCreate() {
        pantalonService.deleteAll();
        Pantalon pantalon1 = new Pantalon("Lewis", "Azul", 32, true);
        Pantalon pantalonCreado = pantalonService.create(pantalon1);
        Long id = pantalonCreado.getId();

        assertNotNull(id);

    }

    @Test
    void testDelete() {
        pantalonService.deleteAll();
        Pantalon pantalon1 = new Pantalon("Lewis", "Azul", 32, true);
        Pantalon pantalonCreado = pantalonService.create(pantalon1);
        Long id = pantalonCreado.getId();
        assertTrue(id > 0);

        pantalonService.delete(id);

        Optional<Pantalon> resultado = pantalonService.get(id);
        assertTrue(resultado.isEmpty());

    }

    @Test
    void testGet() {
        pantalonService.deleteAll();
        // Paso 1: Crear pantalones de prueba
        Pantalon pantalon1 = new Pantalon("Lewis", "Azul", 32, true);
        Pantalon pantalon2 = new Pantalon("Nike", "Negro", 30, false);
        Pantalon pantalon3 = new Pantalon("Jeans", "Gris", 38, true);

        // Paso 2: Guardarlos en la base de datos

        pantalonService.create(pantalon1);
        pantalonService.create(pantalon2);
        pantalonService.create(pantalon3);

        List<Pantalon> listaPantalones = pantalonService.get();

        long longitud = listaPantalones.size();

        assertEquals(3, longitud);

    }

    @Test
    void testGet2() {
        pantalonService.deleteAll();
        // Paso 1: Crear pantalones de prueba
        Pantalon pantalon1 = new Pantalon("Lewis", "Azul", 32, true);
        Pantalon pantalon2 = new Pantalon("Nike", "Negro", 30, false);
        Pantalon pantalon3 = new Pantalon("Jeans", "Gris", 38, true);

        // Paso 2: Guardarlos en la base de datos

        pantalonService.create(pantalon1);
        pantalonService.create(pantalon2);
        pantalonService.create(pantalon3);

        List<Pantalon> listaPantalones = pantalonService.get();
        Pantalon pantalonBuscado = listaPantalones.get(1);

        String marca = pantalonBuscado.getMarca();

        assertEquals("Nike", marca);

    }

    @Test
    void testUpdate() {
        pantalonService.deleteAll();
        // Paso 1: Crear pantalones de prueba
        Pantalon pantalon1 = new Pantalon("Lewis", "Azul", 32, true);

        // Paso 2: Guardarlos en la base de datos

        pantalonService.create(pantalon1);

        List<Pantalon> listaPantalones = pantalonService.get();
        Pantalon pantalonBuscado = listaPantalones.get(0);

        pantalonBuscado.setMarca("Shorts");
        pantalonBuscado.setColor("Negro");
        pantalonBuscado.setTalla(38);
        pantalonBuscado.setPlanchado(false);

        Long id = pantalonBuscado.getId();

        assertEquals(id, pantalonBuscado.getId());
        assertEquals("Shorts", pantalonBuscado.getMarca());
        assertEquals(38, pantalonBuscado.getTalla());
        assertEquals("Negro", pantalonBuscado.getColor());
        assertEquals(false, pantalonBuscado.isPlanchado());

    }
}
