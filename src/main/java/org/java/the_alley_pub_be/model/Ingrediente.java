package org.java.the_alley_pub_be.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ingredienti")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome dell'ingrediente non può essere lasciato vuoto")
    private String name;

    @Lob
    private String descrizione;

    @ManyToMany(mappedBy = "ingredienti")
    private List<Cibo> cibi;

    //---------------------------GETTER--------------------------------

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescrizione() {
        return descrizione;
    }
    public List<Cibo> getCibi() {
        return cibi;
    }
    
    //---------------------------SETTER--------------------------------

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.nome = name;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public void setCibi(List<Cibo> cibi) {
        this.cibi = cibi;
    }

}
