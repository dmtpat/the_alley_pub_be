package org.java.the_alley_pub_be.controller;

import java.util.List;
import java.util.Optional;

import org.java.the_alley_pub_be.model.Bevanda;
import org.java.the_alley_pub_be.repository.BevandaRepository;
import org.java.the_alley_pub_be.repository.CategoriaRepository;
import org.java.the_alley_pub_be.repository.IngredienteRepository;
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
@RequestMapping("/bevande")
public class BevandaController {

    private final IngredienteRepository ingredienteRepository;
    private final CategoriaRepository categoriaRepository;
    private final BevandaRepository bevandaRepository;

    public BevandaController(BevandaRepository bevandaRepository, CategoriaRepository categoriaRepository, IngredienteRepository ingredienteRepository) {
        this.bevandaRepository = bevandaRepository;
        this.categoriaRepository = categoriaRepository;
        this.ingredienteRepository = ingredienteRepository;
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

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("bevanda", new Bevanda());
        model.addAttribute("categorie", categoriaRepository.findAll());

        return "bevande/create-edit";
    }

    @PostMapping("/create")
    private String store(
        @Valid @ModelAttribute("bevanda") Bevanda formBevanda,
                BindingResult bindingResult,
                        Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredienti", ingredienteRepository.findAll());
            model.addAttribute("categorie", categoriaRepository.findAll());
            return "bevande/create-edit";
        }
        bevandaRepository.save(formBevanda);
        return "redirect:/bevande";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("bevanda", bevandaRepository.findById(id).get());
        model.addAttribute("ingredienti", ingredienteRepository.findAll());
        model.addAttribute("categorie", categoriaRepository.findAll());
        model.addAttribute("edit", true);
        return "bevande/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(
        @Valid @ModelAttribute("bevanda") Bevanda formBevanda,
                BindingResult bindingResult,
                        Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredienti", ingredienteRepository.findAll());
            model.addAttribute("categorie", categoriaRepository.findAll());
            return "bevande/create-edit";
        }
        bevandaRepository.save(formBevanda);
        return "redirect:/bevande/" + formBevanda.getId();
    }
    
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        bevandaRepository.deleteById(id);
        return "redirect:/bevande";
    }
}
