package com.example.game_s.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import com.example.game_s.models.Game;
import com.example.game_s.repositories.GameRepository;
import com.example.game_s.repositories.PlayerRepository;

@Controller
public class GameController {
    
    private final PlayerRepository repoPlayer;
    private final GameRepository repo;

    public GameController(PlayerRepository repoPlayer, GameRepository repo){
        this.repoPlayer = repoPlayer;
        this.repo = repo;
    }

    @GetMapping("/game/create")
    public String create(){
        return "games/create";
    }

    @GetMapping("/games")
    public String index(Model model){
        model.addAttribute("games", repo.findAll());
        return "games/index";
    }

    @PostMapping("/games/store")
    public String store(@ModelAttribute Game game){
        repo.save(game);
        return "redirect:/games";
    }

    @GetMapping("/games/show")
    public String show(Model model, Long id){
        model.addAttribute("game", repo.findById(id).orElse(null));
        model.addAttribute("player", repoPlayer.findAll());
        return "games/show";
    }

    @PostMapping("/games/update")
    public String update(@ModelAttribute Game game){
        repo.save(game);
        return "redirect:/games";
    }

    @PostMapping("/games/delete")
    public String delete(Long id){
        repo.deleteById(id);
        return "redirect:/games";
    }
}
