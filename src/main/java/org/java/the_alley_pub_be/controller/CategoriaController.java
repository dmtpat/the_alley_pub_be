package org.java.the_alley_pub_be.controller;

import java.util.List;
import java.util.Optional;

import org.java.the_alley_pub_be.model.Categoria;
import org.java.the_alley_pub_be.repository.CategoriaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model){
        Optional<Categoria> categoriaAttempt = categoriaRepository.findById(id);
        if(categoriaAttempt.isEmpty()){
            model.addAttribute("categoria", null);
        }else{
            model.addAttribute("categoria", categoriaAttempt.get());
        }

        return "categorie/show";
    }
}
