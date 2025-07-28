package es.cic.curso25.proy008.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import es.cic.curso25.proy008.model.Propietario;
import es.cic.curso25.proy008.service.PantalonService;
import es.cic.curso25.proy008.service.PropietarioService;

@RestController
@RequestMapping("/pantalon")
public class PantalonController {

    @Autowired
    private PantalonService pantalonService;

    @Autowired
    private PropietarioService propietarioService;

    @GetMapping
    public List<Pantalon> get() {
        return pantalonService.get();
    }

    // @GetMapping("/{id}")
    // public Optional<Pantalon> get(@PathVariable long id) {

    //     Optional<Pantalon> pantalon = pantalonService.get(id);

    //     return pantalon;
    // }

    @GetMapping("/{id}")
    public ResponseEntity<Pantalon> get(@PathVariable long id) {
        Optional<Pantalon> pantalon = pantalonService.get(id);
        return pantalon.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pantalon create(@RequestBody Pantalon pantalon) {

        if (pantalon.getId() != null) {
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
    public ResponseEntity<Pantalon> update(@PathVariable long id, @RequestBody Pantalon pantalon) {

        // Validar si el pantalón con ese ID existe
        Optional<Pantalon> existente = pantalonService.get(id);
        if (!existente.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Validar que el ID del body sea nulo o coincida con el de la URL
        if (pantalon.getId() != null && !pantalon.getId().equals(id)) {
            throw new ModificationSecurityException("El ID del path no coincide con el del body.");
        }

        // Establecer el ID correctamente
        pantalon.setId(id);

        // Actualizar y devolver
        Pantalon actualizado = pantalonService.update(pantalon);
        return ResponseEntity.ok(actualizado);
    }

    // @PutMapping("/{id}")
    // public Pantalon update(@PathVariable long id, @RequestBody Pantalon pantalon)
    // {

    // pantalon.setId(id);

    // Pantalon pantalonActualizado = pantalonService.update(pantalon);

    // return pantalonActualizado;
    // }

    // @PostMapping("/propietario")
    // public Propietario create(@RequestBody Propietario propietario) {
    // Propietario propietarioCreado = propietarioService.create(propietario);

    // return propietarioCreado;
    // }
}
