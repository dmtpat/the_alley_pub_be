package org.java.the_alley_pub_be.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "The username can't be empty or blank")
    private String username;

    @NotBlank(message = "The password can't be blank or empty")
    @Size(min = 8, message = "The password must contain at least 8 characters")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_user",
                joinColumns = @JoinColumn(name = "role_id"),
                        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<Role> roles;

    //--------------------------------GETTER----------------------------------------
    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    //--------------------------------SETTER----------------------------------------
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
