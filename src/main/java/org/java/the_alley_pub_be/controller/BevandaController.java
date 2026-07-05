package org.java.the_alley_pub_be.controller;

import java.util.List;
import java.util.Optional;

import org.java.the_alley_pub_be.model.Bevanda;
import org.java.the_alley_pub_be.repository.BevandaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bevande")
public class BevandaController {

    private final BevandaRepository bevandaRepository;

    public BevandaController(BevandaRepository bevandaRepository) {
        this.bevandaRepository = bevandaRepository;
    }

    @GetMapping
    public String index(@RequestParam(value = "valoreRicerca", required = false) String valore, Model model) {
        List<Bevanda> bevande = bevandaRepository.findAll();
        if (valore != null) {
            bevande = bevandaRepository.findByNameContainingIgnoreCase(valore);
        }
        model.addAttribute("bevande", bevande);
        return "bevande/index";
    }
    
    @RequestMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Bevanda> bevandaAttempt = bevandaRepository.findById(id);
        if (bevandaAttempt.isEmpty()) {
            model.addAttribute("bevanda", null);
        } else {
            model.addAttribute("bevanda", bevandaAttempt.get());
        }
        return "bevande/show";
    }
}
