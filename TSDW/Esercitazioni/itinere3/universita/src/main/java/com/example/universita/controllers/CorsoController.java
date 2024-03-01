package com.example.universita.controllers;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.example.universita.models.Corso;
import com.example.universita.repositories.CorsoRepository;

@Controller
public class CorsoController {
    
    private CorsoRepository repo;

    public CorsoController(CorsoRepository repo){
        this.repo=repo;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("Corsi", repo.findAll());
        return "index";
    }

    @GetMapping("/create")
    public String create(){
        return "create";
    }

    @PostMapping("/store")
    public String store(Corso corso){
        repo.save(corso);
        return "redirect:/";
    }

    @GetMapping("/show")
    public String show(Long id, Model model){
        model.addAttribute("corso", repo.findById(id).orElse(null));
        return "show";
    }    

    @GetMapping("/edit")
    public String edit(Long id, Model model){
        model.addAttribute("corso", repo.findById(id).orElse(null));
        return "edit";
    }  
    
    @PostMapping("/update")
    public String update(Corso corso){
        repo.save(corso);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(Long id){
        repo.deleteById(id);
        return "redirect:/";
    }

}
