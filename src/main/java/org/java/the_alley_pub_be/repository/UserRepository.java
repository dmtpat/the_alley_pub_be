package org.java.the_alley_pub_be.repository;

import java.util.Optional;

import org.java.the_alley_pub_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    Optional<User> findByUsername(String username);

}
