package org.java.the_alley_pub_be.controller;

import org.java.the_alley_pub_be.repository.CiboRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/food")
public class CiboController {

    private final CiboRepository ciboRepository;

    public CiboController(CiboRepository ciboRepository) {
        this.ciboRepository = ciboRepository;
    }
    
}
