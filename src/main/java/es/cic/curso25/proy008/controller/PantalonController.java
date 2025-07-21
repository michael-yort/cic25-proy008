package es.cic.curso25.proy008.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso25.proy008.service.PantalonService;

@RestController
@RequestMapping("/habito")
public class PantalonController {

    @Autowired
    private PantalonService pantalonService;

   

}
