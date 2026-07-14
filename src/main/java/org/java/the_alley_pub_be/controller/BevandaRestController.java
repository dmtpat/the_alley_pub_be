package org.java.the_alley_pub_be.controller;

import java.util.List;

import org.java.the_alley_pub_be.model.Bevanda;
import org.java.the_alley_pub_be.service.BevandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bevande")
public class BevandaRestController {

    @Autowired
    private BevandaService bevandaService;

    //--- INDEX ---------
    @GetMapping
    public ResponseEntity<List<Bevanda>> index(
        @Valid @RequestParam(required = false) String name,
                @Valid @RequestParam(required = false) String categoria
    ) {
        if (name != null) {
            if (bevandaService.findByName(name).isEmpty()) {
                return new ResponseEntity<List<Bevanda>>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<Bevanda>>(bevandaService.findByName(name), HttpStatus.OK);
        }
        if (categoria != null) {
            if (bevandaService.findByCategoriaName(categoria).isEmpty()) {
                return new ResponseEntity<List<Bevanda>>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<Bevanda>>(bevandaService.findByCategoriaName(categoria), HttpStatus.OK);
        }
        return new ResponseEntity<List<Bevanda>>(bevandaService.findAll(), HttpStatus.OK);
    }
    //--- SHOW ---------
    @GetMapping("/{id}")
    public ResponseEntity<Bevanda> show(@Valid @PathVariable Integer id) {
        if (bevandaService.findById(id).isEmpty()) {
            return new ResponseEntity<Bevanda>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Bevanda>(bevandaService.getById(id), HttpStatus.OK);
    }

}
