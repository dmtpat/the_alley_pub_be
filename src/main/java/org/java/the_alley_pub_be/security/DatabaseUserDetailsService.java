package org.java.the_alley_pub_be.security;

import java.util.Optional;

import org.java.the_alley_pub_be.model.User;
import org.java.the_alley_pub_be.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DatabaseUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DatabaseUserDetailsService()

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        
        Optional<User> userAttempt = userRepository.findByUsername(username);
        if(userAttempt.isEmpty())
        {
            throw new UsernameNotFoundException("There are NO user available with username " + username);
        }
        return new DatabaseUserDetails(userAttempt.get());
    }

}
