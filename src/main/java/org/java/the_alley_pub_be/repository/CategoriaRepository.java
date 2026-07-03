package org.java.the_alley_pub_be.repository;

import java.util.List;

import org.java.the_alley_pub_be.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    public List<Categoria> findByNameContainingIgnoreCase(String name);
}
