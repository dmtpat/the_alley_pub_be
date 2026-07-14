package org.java.the_alley_pub_be.controller;

import java.util.List;

import org.java.the_alley_pub_be.model.Cibo;
import org.java.the_alley_pub_be.service.CiboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cibi")
public class CiboRestController {

    @Autowired
    private CiboService ciboService;

    //--- INDEX ---------
    @GetMapping
    public ResponseEntity<List<Cibo>> index(@Valid @RequestParam(required = false) String name) {
        if (name != null) {
            if (ciboService.findByName(name).isEmpty()) {
                return new ResponseEntity<List<Cibo>>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<Cibo>>(ciboService.findByName(name), HttpStatus.OK);
        }
        return new ResponseEntity<List<Cibo>>(ciboService.findAll(), HttpStatus.OK);
    }
    //--- SHOW ---------
    @GetMapping("/{id}")
    public ResponseEntity<Cibo> show(@Valid @PathVariable Integer id) {
        if (ciboService.findById(id).isEmpty()) {
            return new ResponseEntity<Cibo>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cibo>(ciboService.getById(id), HttpStatus.OK);
    }
    //--- STORE ---------
    @PostMapping
    public ResponseEntity<Cibo> store(@Valid @RequestBody Cibo cibo) {
        return new ResponseEntity<Cibo>(ciboService.create(cibo), HttpStatus.CREATED);
    }

    //--- UPDATE ---------
    @PutMapping("/{id}")
    public ResponseEntity<Cibo> update(@Valid @PathVariable Integer id) {
        if (ciboService.findById(id).isEmpty()) {
            return new ResponseEntity<Cibo>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cibo>(ciboService.getById(id), HttpStatus.OK);
    }
    //--- DELETE ---------
    @DeleteMapping("/{id}")
    public ResponseEntity<Cibo> delete(@Valid @PathVariable Integer id) {
        if (ciboService.findById(id).isEmpty()) {
            return new ResponseEntity<Cibo>(HttpStatus.NOT_FOUND);
        }
        ciboService.deleteById(id);
        return new ResponseEntity<Cibo>(HttpStatus.OK);
    }
}
