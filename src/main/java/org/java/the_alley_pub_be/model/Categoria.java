package org.java.the_alley_pub_be.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "categorie")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "Il nome della categoria non può essere vuoto")
    private String name;

    @ManyToMany(mappedBy = "categorie")
    @JsonIgnore
    private List<Cibo> cibi;

    @ManyToMany(mappedBy = "categorie")
    @JsonIgnoreProperties("categorie")
    private List<Bevanda> bevande;


    //---------------------------GETTER--------------------------------

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Cibo> getCibi() {
        return cibi;
    }
    public List<Bevanda> getBevande() {
        return bevande;
    }
    
    //---------------------------SETTER--------------------------------

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCibi(List<Cibo> cibi) {
        this.cibi = cibi;
    }
    public void setBevande(List<Bevanda> bevande) {
        this.bevande = bevande;
    }

}
