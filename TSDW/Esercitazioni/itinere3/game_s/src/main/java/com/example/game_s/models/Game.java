package com.example.game_s.models;

import jakarta.persistence.*;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    @ManyToOne
    @JoinColumn(name="player")

    private Long id;
    private String nome;
    private float price;
    private Player player;

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
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game() {
    }

    public Game(Long id, String nome, float price, Player player) {
        this.id = id;
        this.nome = nome;
        this.price = price;
        this.player = player;
    }
}
