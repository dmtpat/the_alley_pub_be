package org.java.the_alley_pub_be.controller;

import java.util.List;

import org.java.the_alley_pub_be.model.Ingrediente;
import org.java.the_alley_pub_be.repository.IngredienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

}
