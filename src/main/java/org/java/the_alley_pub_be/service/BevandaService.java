package org.java.the_alley_pub_be.service;

import java.util.List;
import java.util.Optional;

import org.java.the_alley_pub_be.model.Bevanda;
import org.java.the_alley_pub_be.model.Categoria;
import org.java.the_alley_pub_be.repository.BevandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BevandaService {

    @Autowired
    private BevandaRepository bevandaRepository;

    public List<Bevanda> findAll() {
        return bevandaRepository.findAll();
    }

    public Optional<Bevanda> findById(Integer id) {
        return bevandaRepository.findById(id);
    }

    public Bevanda getById(Integer id) {
        Optional<Bevanda> bevandaAttempt = findById(id);
        if (bevandaAttempt.isEmpty()) {
            //throw exception
        }
        return bevandaAttempt.get();
    }

    public List<Bevanda> findByName(String name) {
        return bevandaRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Bevanda> findByCategoria(Categoria categoria) {
        return bevandaRepository.findByCategorieIgnoreCase(categoria);
    }

    public List<Bevanda> findByCategoriaName(String name) {
        return bevandaRepository.findByCategorieNameContainingIgnoreCase(name);
    }

    public Bevanda create(Bevanda bevanda) {
        return bevandaRepository.save(bevanda);
    }

    public Bevanda update(Bevanda bevanda) {
        return bevandaRepository.save(bevanda);
    }

    public void delete(Bevanda bevanda) {
        bevandaRepository.delete(bevanda);
    }

    public void deleteById(Integer id) {
        Bevanda bevanda = getById(id);
        delete(bevanda);
    }

    private Boolean existsById(Integer id) {
        return bevandaRepository.existsById(id);
    }

    private Boolean exists(Bevanda bevanda) {
        return existsById(bevanda.getId());
    }
}
