package org.java.the_alley_pub_be.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/cibi/create", "/bevande/create").hasAuthority("ADMIN")
                .requestMatchers("/cibi/edit/**", "/bevande/edit/**").hasAuthority("ADMIN")
                .requestMatchers("/cibi/delete/**", "/bevande/delete/**", "/categorie/delete/**",
                        "/ingredienti/delete/**")
                .hasAuthority("ADMIN")
                .requestMatchers("/cibi/**", "/bevande/**", "/categorie/**", "/ingredienti/**")
                .hasAnyAuthority("ADMIN", "USER")
                .requestMatchers("/cibi", "/bevande", "/categorie", "/ingredienti").permitAll()
                .requestMatchers("/").permitAll()
                .anyRequest().authenticated())
                .formLogin(form -> form.permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(exception -> exception.accessDeniedPage(null))
                .cors(cors -> cors.disable())
                .csrf(csfr -> csfr.disable());

        return http.build();
    }
    
    @Bean
    DatabaseUserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}
