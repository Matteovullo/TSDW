package com.example.client_order_s.models;

import jakarta.persistence.*;

@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    Long id;
    Integer tavolo;
    Float prezzo;

    @ManyToOne
    @JoinColumn(name="client")

    Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTavolo() {
        return tavolo;
    }

    public void setTavolo(Integer tavolo) {
        this.tavolo = tavolo;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Order(Long id, Integer tavolo, Float prezzo, Client client) {
        this.id = id;
        this.tavolo = tavolo;
        this.prezzo = prezzo;
        this.client = client;
    }

    public Order() {
    }
    
}
