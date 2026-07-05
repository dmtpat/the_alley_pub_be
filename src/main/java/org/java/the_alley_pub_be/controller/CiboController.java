package org.java.the_alley_pub_be.controller;

import java.util.List;
import java.util.Optional;

import org.java.the_alley_pub_be.model.Cibo;
import org.java.the_alley_pub_be.repository.CiboRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cibi")
public class CiboController {

    private final CiboRepository ciboRepository;

    public CiboController(CiboRepository ciboRepository) {
        this.ciboRepository = ciboRepository;
    }

    @GetMapping
    public String index(@RequestParam(value = "valoreRicerca", required = false) String valore, Model model) {
        List<Cibo> cibi = ciboRepository.findAll();

        if (valore != null) {
            cibi = ciboRepository.findByNameContainingIgnoreCase(valore);
        }
        model.addAttribute("cibi", cibi);
        return "cibi/index";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Cibo> ciboAttempt = ciboRepository.findById(id);
        if (ciboAttempt.isEmpty()) {
            model.addAttribute("cibo", null);
        } else {
            model.addAttribute("cibo", ciboAttempt.get());
        }
        
        return "cibi/show";
    }
}
