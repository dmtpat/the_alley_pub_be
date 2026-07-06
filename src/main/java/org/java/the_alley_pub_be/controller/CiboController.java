package org.java.the_alley_pub_be.controller;

import java.util.List;
import java.util.Optional;

import org.java.the_alley_pub_be.model.Cibo;
import org.java.the_alley_pub_be.repository.CategoriaRepository;
import org.java.the_alley_pub_be.repository.CiboRepository;
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
@RequestMapping("/cibi")
public class CiboController {

    private final CategoriaRepository categoriaRepository;
    private final IngredienteRepository ingredienteRepository;
    private final CiboRepository ciboRepository;

    public CiboController(CiboRepository ciboRepository, IngredienteRepository ingredienteRepository, CategoriaRepository categoriaRepository) {
        this.ciboRepository = ciboRepository;
        this.ingredienteRepository = ingredienteRepository;
        this.categoriaRepository = categoriaRepository;
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
    
    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("cibo", new Cibo());
        model.addAttribute("ingredienti", ingredienteRepository.findAll());
        model.addAttribute("categorie", categoriaRepository.findAll());

        return "cibi/create-edit";
    }
    
    @PostMapping("/create")
    public String store(
        @Valid @ModelAttribute("cibo") Cibo formCibo,
                BindingResult bindingResult,
                        Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredienti", ingredienteRepository.findAll());
            model.addAttribute("categorie", categoriaRepository.findAll());
            return "cibi/create-edit";
        }
        ciboRepository.save(formCibo);
        return "redirect:/cibi";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("cibo", ciboRepository.findById(id).get());
        model.addAttribute("ingredienti", ingredienteRepository.findAll());
        model.addAttribute("categorie", categoriaRepository.findAll());
        model.addAttribute("edit", true);
        return "cibi/create-edit";
    }
    
    @PostMapping("/edit/{id}")
    public String update(
        @Valid @ModelAttribute("cibo") Cibo formCibo,
                BindingResult bindingResult,
                        Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredieinti", ingredienteRepository.findAll());
            model.addAttribute("categorie", categoriaRepository.findAll());
            return "cibi/create-edit";
        }
        ciboRepository.save(formCibo);
        return "redirect:/cibi/" + formCibo.getId();
    }
}
