package org.java.the_alley_pub_be.controller;

import java.util.List;
import java.util.Optional;

import org.java.the_alley_pub_be.model.Categoria;
import org.java.the_alley_pub_be.repository.CategoriaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

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
    public String show(@PathVariable Integer id, Model model) {
        Optional<Categoria> categoriaAttempt = categoriaRepository.findById(id);
        if (categoriaAttempt.isEmpty()) {
            model.addAttribute("categoria", null);
        } else {
            model.addAttribute("categoria", categoriaAttempt.get());
        }

        return "categorie/show";
    }
    
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorie/create-edit";
    }

    @PostMapping("/create")
    public String store(
        @Valid @ModelAttribute("categoria") Categoria formCategoria,
                BindingResult bindingResult,
                        Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "categorie/create-edit";
        }
        categoriaRepository.save(formCategoria);
        return "redirect:/categorie";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("categoria", categoriaRepository.findById(id).get());
        model.addAttribute("edit", true);
        return "categorie/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(
        @Valid @ModelAttribute("categoria") Categoria formCategoria,
                BindingResult bindingResult,
                        Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "categorie/create-edit";
        }
        categoriaRepository.save(formCategoria);
        return "redirect:/categorie/" + formCategoria.getId();
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        categoriaRepository.deleteById(id);
        return "redirect:/categorie";
    }
}
