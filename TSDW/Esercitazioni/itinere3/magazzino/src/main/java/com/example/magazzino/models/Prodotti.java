package com.example.magazzino.models;

import jakarta.persistence.*;

@Entity
public class Prodotti {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    Long id;
    String nome_prodotto;
    Integer giacenza;
    Float prezzo;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome_prodotto() {
        return nome_prodotto;
    }
    public void setNome_prodotto(String nome_prodotto) {
        this.nome_prodotto = nome_prodotto;
    }
    public Integer getGiacenza() {
        return giacenza;
    }
    public void setGiacenza(Integer giacenza) {
        this.giacenza = giacenza;
    }
    public Float getPrezzo() {
        return prezzo;
    }
    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    
}
