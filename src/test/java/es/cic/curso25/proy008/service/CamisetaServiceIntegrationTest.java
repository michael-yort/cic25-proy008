package es.cic.curso25.proy008.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.cic.curso25.proy008.model.Camiseta;

@SpringBootTest
public class CamisetaServiceIntegrationTest {

    @Autowired
    private CamisetaService camisetaService;

    @Test
    void testCreate() {
        Camiseta camiseta1 = new Camiseta(40, "azul", "Nike", true);
        camisetaService.create(camiseta1);

        assertTrue(camiseta1.getId() > 0);

    }

    @Test
    void testGetById() {
        Camiseta camiseta1 = new Camiseta(41, "verde", "Adidas", false);
        camiseta1 = camisetaService.create(camiseta1);

        Camiseta encontrada = camisetaService.getById(camiseta1.getId()).orElse(null);
        assertTrue(encontrada != null);
    }

    @Test
    void testGetAll() {

        Camiseta camiseta1 = new Camiseta(38, "gris", "Nike", true);
        Camiseta camiseta2 = new Camiseta(40, "azul", "Adidas", false);

        camisetaService.create(camiseta1);
        camisetaService.create(camiseta2);

        List<Camiseta> camisetas = camisetaService.getAll();
        assertTrue(camisetas.size() == 2);
    }

    @Test
    void testUpdate() {
        Camiseta camiseta1 = new Camiseta(42, "negro", "Versace", true);
        camiseta1 = camisetaService.create(camiseta1);

        camiseta1.setColor("rosa");
        camisetaService.update(camiseta1);

        assertEquals("rosa", camiseta1.getColor());
    }

    @Test
    void testDelete() {

        Camiseta camiseta1 = new Camiseta(41, "verde", "Zara", false);
        camisetaService.create(camiseta1);
        long id = camiseta1.getId();

        camisetaService.delete(id);

        Optional<Camiseta> eliminada = camisetaService.getById(id);
        assertTrue(eliminada.isEmpty(), "La camiseta1 no existe en base de datos");
    }

}
