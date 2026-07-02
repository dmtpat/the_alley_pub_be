package org.java.the_alley_pub_be.controller;

import org.java.the_alley_pub_be.repository.BevandaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bevande")
public class BevandaController {

    private final BevandaRepository bevandaRepository;

    public BevandaController(BevandaRepository bevandaRepository) {
        this.bevandaRepository = bevandaRepository;
    }
}
