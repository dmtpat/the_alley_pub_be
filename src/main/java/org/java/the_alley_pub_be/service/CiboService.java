package org.java.the_alley_pub_be.service;

import java.util.List;
import java.util.Optional;

import org.java.the_alley_pub_be.model.Cibo;
import org.java.the_alley_pub_be.repository.CiboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CiboService {

    @Autowired
    private CiboRepository ciboRepository;

    public List<Cibo> findAll() {
        return ciboRepository.findAll();
    }

    public Optional<Cibo> findById(Integer id) {
        return ciboRepository.findById(id);
    }

    public Cibo getById(Integer id) {
        Optional<Cibo> ciboAttempt = findById(id);
        if (ciboAttempt.isEmpty()) {
            //lanciare not found exception
        }
        return ciboAttempt.get();
    }

    public List<Cibo> findByName(String name) {
        return ciboRepository.findByNameContainingIgnoreCase(name);
    }

    public Cibo create(Cibo cibo) {
        return ciboRepository.save(cibo);
    }

    public Cibo update(Cibo cibo) {
        return ciboRepository.save(cibo);
    }

    public void delete(Cibo cibo) {
        ciboRepository.delete(cibo);
    }

    public void deleteById(Integer id) {
        Cibo cibo = getById(id);
        ciboRepository.delete(cibo);
    }

    public Boolean existsById(Integer id) {
        return ciboRepository.existsById(id);
    }

    public Boolean exists(Cibo cibo) {
        return existsById(cibo.getId());
    }
}
