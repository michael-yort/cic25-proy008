package es.cic.curso25.proy008.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import es.cic.curso25.proy008.model.Camiseta;
import es.cic.curso25.proy008.service.CamisetaService;

@RestController
@RequestMapping("/camiseta")
public class CamisetaController {

    @Autowired
    private CamisetaService camisetaService;

    @GetMapping
    public List<Camiseta> getAll() {
        return camisetaService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Camiseta> getById(@PathVariable long id) {
        return camisetaService.getById(id);
    }

    @PostMapping
    public Camiseta create(@RequestBody Camiseta camiseta) {
        return camisetaService.create(camiseta);
    }

    @PutMapping("/{id}")
    public Camiseta update(@PathVariable long id, @RequestBody Camiseta camiseta) {
        camiseta.setId(id);
        return camisetaService.update(camiseta);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        camisetaService.delete(id);
    }
}
