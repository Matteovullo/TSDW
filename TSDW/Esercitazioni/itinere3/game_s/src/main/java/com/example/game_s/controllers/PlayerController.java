package com.example.game_s.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.example.game_s.models.Player;
import com.example.game_s.repositories.PlayerRepository;

@Controller
public class PlayerController {
    
    private final PlayerRepository repo;

    public PlayerController(PlayerRepository repo){
        this.repo = repo;
    }
    
    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/players/create")
    public String create(){
        return "players/create";
    }

    @GetMapping("/players")
    public String index(Model model){
        model.addAttribute("players", repo.findAll());  // Cambiato "player" a "players" per evitare confusione
        return "players/index";  // Cambiato "players/create" a "players/index"
    }

    @PostMapping("/players/store")
    public String store(Player player){
        repo.save(player);
        return "redirect:/players";  // Cambiato "redirect:players" a "redirect:/players"
    }

    @GetMapping("/players/edit")
    public String show(Model model, Long id){  // Cambiato l'annotazione da @PostMapping a @GetMapping
        model.addAttribute("player", repo.findById(id).orElse(null));
        return "players/edit";
    }

    @PostMapping("/players/update")
    public String update(Player player){
        repo.save(player);
        return "redirect:/players";  // Cambiato "redirect:players" a "redirect:/players"
    }

    @PostMapping("/players/delete")
    public String delete(Long id){
        repo.deleteById(id);
        return "redirect:/players";  // Cambiato "redirect:players" a "redirect:/players"
    }
}
