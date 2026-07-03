package org.java.the_alley_pub_be.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "bevande")
public class Bevanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome della bevanda non può esseere lasciato vuoto")
    private String name;

    @Min(value = 0, message = "Il volume della bevande non può essere negativo")
    private Integer dimensione;

    @Lob
    private String descrizione;

    @NotEmpty(message = "Deve sempre essere inserito un prezzo")
    @Min(value = 0, message = "Il prezzo non può essere negativo")
    private Double prezzo;

    @ManyToMany
    @JoinTable(
        name = "bevanda_categoria",
                joinColumns = @JoinColumn(name = "bevanda_id"),
                        inverseJoinColumns = @JoinColumn(name= "categoria_id")
    )
    private List<Categoria> categorie;
    //---------------------------GETTER--------------------------------

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getDimensione() {
        return dimensione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Double getPrezzo() {
        return prezzo;
    }
    public List<Categoria> getCategorie() {
        return categorie;
    }
    
    //---------------------------SETTER--------------------------------

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDimensione(Integer dimensione) {
        this.dimensione = dimensione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }
    public void setCategorie(List<Categoria> categorie) {
        this.categorie = categorie;
    }


}
