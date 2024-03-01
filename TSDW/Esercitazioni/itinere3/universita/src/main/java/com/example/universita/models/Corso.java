package com.example.universita.models;

import jakarta.persistence.*;

@Entity
public class Corso {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    Long id;
    String nome;
    Integer cfu;
    String dipartimento;

    public Corso(){}    

    public Corso(Long id, String nome, Integer cfu, String dipartimento) {
        this.id = id;
        this.nome = nome;
        this.cfu = cfu;
        this.dipartimento = dipartimento;
    }
    
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
    public Integer getCfu() {
        return cfu;
    }
    public void setCfu(Integer cfu) {
        this.cfu = cfu;
    }
    public String getDipartimento() {
        return dipartimento;
    }
    public void setDipartimento(String dipartimento) {
        this.dipartimento = dipartimento;
    }

    
}
