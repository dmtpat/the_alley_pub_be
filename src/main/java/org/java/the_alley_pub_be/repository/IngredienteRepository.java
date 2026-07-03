package org.java.the_alley_pub_be.repository;

import java.util.List;

import org.java.the_alley_pub_be.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {

    public List<Ingrediente> findByNameContainingIgnoreCase(String Name);
}
