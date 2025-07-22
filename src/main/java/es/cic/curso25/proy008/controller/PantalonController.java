package es.cic.curso25.proy008.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso25.proy008.exception.ModificationSecurityException;
import es.cic.curso25.proy008.model.Pantalon;
import es.cic.curso25.proy008.service.PantalonService;

@RestController
@RequestMapping("/pantalon")
public class PantalonController {

    @Autowired
    private PantalonService pantalonService;

    @GetMapping
    public List<Pantalon> get() {
        return pantalonService.get();
    }

    @GetMapping("/{id}")
    public Optional<Pantalon> get(@PathVariable long id) {

        Optional<Pantalon> pantalon = pantalonService.get(id);

        return pantalon;
    }

    @PostMapping
    public Pantalon create(@RequestBody Pantalon pantalon) {

        if (pantalon.getId() !=null) {
            throw new ModificationSecurityException();
            
        }
        Pantalon pantalonCreado = pantalonService.create(pantalon);

        return pantalonCreado;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        pantalonService.delete(id);

    }

    @PutMapping("/{id}")
    public Pantalon update(@PathVariable long id, @RequestBody Pantalon pantalon) {

        pantalon.setId(id);

        Pantalon pantalonActualizado = pantalonService.update(pantalon);

        return pantalonActualizado;
    }

}
