package org.java.the_alley_pub_be.controller;

import org.java.the_alley_pub_be.repository.IngredienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredienti")
public class IngredienteController {

    public final IngredienteRepository ingredienteRepository;

    public IngredienteController(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

}
