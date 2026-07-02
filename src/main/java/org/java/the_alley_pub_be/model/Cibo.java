package org.java.the_alley_pub_be.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "cibo")
public class Cibo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome del cibo non può esseere lasciato vuoto")
    private String nome;

    @Lob
    private String descrizione;

    @NotEmpty(message = "Deve sempre essere insewrito un prezzo")
    @Min(value = 0, message = "Il prezzo non può essere negativo")
    private Double prezzo;

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

}
