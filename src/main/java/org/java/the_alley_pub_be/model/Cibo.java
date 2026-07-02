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
@Table(name = "cibi")
public class Cibo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome del cibo non può esseere lasciato vuoto")
    private String nome;

    @Lob
    private String descrizione;

    @NotEmpty(message = "Deve sempre essere inserito un prezzo")
    @Min(value = 0, message = "Il prezzo non può essere negativo")
    private Double prezzo;

    @ManyToMany
    @JoinTable(
        name = "cibo_ingrediente",
            joinColumns = @JoinColumn(name = "cibo_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private List<Ingrediente> ingredienti;

    @ManyToMany
    @JoinTable(
        name = "categoria_cibo",
                joinColumns =  @JoinColumn(name = "cibo_id"),
                        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> cetegorie;

    //---------------------------GETTER--------------------------------

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public List<Ingrediente> getIngredienti() {
        return ingredienti;
    }
    public List<Categoria> getCetegorie() {
        return cetegorie;
    }
    
    //---------------------------SETTER--------------------------------

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public void setIngredienti(List<Ingrediente> ingredienti) {
        this.ingredienti = ingredienti;
    }
    public void setCetegorie(List<Categoria> cetegorie) {
        this.cetegorie = cetegorie;
    }

}
