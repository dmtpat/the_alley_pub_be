package org.java.the_alley_pub_be.controller;

import java.util.List;
import java.util.Optional;

import org.java.the_alley_pub_be.model.Ingrediente;
import org.java.the_alley_pub_be.repository.IngredienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ingredienti")
public class IngredienteController {

    public final IngredienteRepository ingredienteRepository;

    public IngredienteController(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    @RequestMapping
    public String index(@RequestParam(value = "valoreRicerca", required = false) String valore, Model model) {

        List<Ingrediente> ingredienti = ingredienteRepository.findAll();
        if (valore != null) {
            ingredienti = ingredienteRepository.findByNameContainingIgnoreCase(valore);
        }
        model.addAttribute("ingredienti", ingredienti);
        return "ingredienti/index";
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Ingrediente> ingredienteAttempt = ingredienteRepository.findById(id);
        if (ingredienteAttempt.isEmpty()) {
            model.addAttribute("ingrediente", null);
        } else {
            model.addAttribute("ingrediente", ingredienteAttempt.get());
        }

        return "ingredienti/show";
    }

}
