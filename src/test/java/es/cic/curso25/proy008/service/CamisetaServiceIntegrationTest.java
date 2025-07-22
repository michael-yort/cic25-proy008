package es.cic.curso25.proy008.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.cic.curso25.proy008.exception.ModificationSecurityException;
import es.cic.curso25.proy008.model.Camiseta;

@SpringBootTest
public class CamisetaServiceIntegrationTest {

    @Autowired
    private CamisetaService camisetaService;

    @Test
    void testCreate() {
        camisetaService.deleteAll();

        Camiseta camiseta1 = new Camiseta(40, "azul", "Nike", true);
        camisetaService.create(camiseta1);

        assertTrue(camiseta1.getId() > 0);

    }

    @Test
    void testGetById() {
        camisetaService.deleteAll();

        Camiseta camiseta1 = new Camiseta(41, "verde", "Adidas", false);
        camisetaService.create(camiseta1);

        Camiseta encontrada = camisetaService.getById(camiseta1.getId()).orElse(null);
        assertTrue(encontrada != null);
    }

    @Test
    void testGetAll() {
        camisetaService.deleteAll();

        Camiseta camiseta1 = new Camiseta(38, "gris", "Nike", true);
        Camiseta camiseta2 = new Camiseta(40, "azul", "Adidas", false);

        camisetaService.create(camiseta1);
        camisetaService.create(camiseta2);

        List<Camiseta> camisetasGetAllTest = camisetaService.getAll();
        assertTrue(camisetasGetAllTest.size() == 2);
    }

    @Test
    void testUpdate() {
        camisetaService.deleteAll();

        Camiseta camiseta1 = new Camiseta(42, "negro", "Versace", true);
        camiseta1 = camisetaService.create(camiseta1);

        camiseta1.setColor("rosa");
        camisetaService.update(camiseta1);

        assertEquals("rosa", camiseta1.getColor());
    }

    @Test
    void testDelete() {
        camisetaService.deleteAll();

        Camiseta camiseta1 = new Camiseta(41, "verde", "Zara", false);
        camisetaService.create(camiseta1);
        Long id = camiseta1.getId();

        camisetaService.delete(id);

        Optional<Camiseta> eliminada = camisetaService.getById(id);
        assertTrue(eliminada.isEmpty(), "La camiseta1 no existe en base de datos");
    }

    @Test
void crearConIdLanzaExcepcion() {
    Camiseta camisetaConId = new Camiseta();
    camisetaConId.setId(99L); // Simula intento de modificación

    assertThrows(ModificationSecurityException.class, () -> {
        camisetaService.create(camisetaConId);
    });
}
}
