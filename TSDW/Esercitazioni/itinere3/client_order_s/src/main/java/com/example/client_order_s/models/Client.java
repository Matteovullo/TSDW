package com.example.client_order_s.models;

import jakarta.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
    Long id;
    String nome;
    Integer eta;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getEta() {
        return eta;
    }
    public void setEta(Integer eta) {
        this.eta = eta;
    }
    
    public Client(Long id, String nome, Integer eta) {
        this.id = id;
        this.nome = nome;
        this.eta = eta;
    }

    public Client() {
    }

}
