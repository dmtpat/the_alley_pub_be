package org.java.the_alley_pub_be.controller;

import java.util.List;

import org.java.the_alley_pub_be.model.Categoria;
import org.java.the_alley_pub_be.repository.CategoriaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/categorie")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public String index(@RequestParam(value = "valoreRicerca", required = false) String valore, Model model) {
        List<Categoria> categorie = categoriaRepository.findAll();

        if (valore != null) {
            categorie = categoriaRepository.findByNameContainingIgnoreCase(valore);
        }
        model.addAttribute("categorie", categorie);
        return "categorie/index";
    }
}
