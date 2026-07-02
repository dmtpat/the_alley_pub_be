package org.java.the_alley_pub_be.model;

import java.util.List;

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
    private String nome;

    @ManyToMany(mappedBy = "categoria")
    private List<Cibo> cibi;

    @ManyToMany(mappedBy = "categoria")
    private List<Bevanda> bevande;


    //---------------------------GETTER--------------------------------

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCibi(List<Cibo> cibi) {
        this.cibi = cibi;
    }
    public void setBevande(List<Bevanda> bevande) {
        this.bevande = bevande;
    }

}
