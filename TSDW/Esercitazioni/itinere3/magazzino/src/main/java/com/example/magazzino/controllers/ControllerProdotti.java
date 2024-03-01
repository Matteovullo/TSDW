package com.example.magazzino.controllers;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.magazzino.models.Prodotti;
import com.example.magazzino.repositories.ProdottiRepository;

@Controller
public class ControllerProdotti {
    
    private final ProdottiRepository r;

    public ControllerProdotti(ProdottiRepository r){
        this.r=r;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("Prodotti", r.findAll());
        return "index";
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @PostMapping("/store")
    public String store(Prodotti prodotti){
        r.save(prodotti);
        return "redirect:/";
    }


    @GetMapping("/show/{id}")
    public String show(Model model, @PathVariable Long id) {
        model.addAttribute("p", r.findById(id).orElse(null));
        return "show";
    }


    @GetMapping("/edit")
    public String edit(Model model, Long id){
        model.addAttribute("Prodotto", r.findById(id).orElse(null));
        return "edit";
    }

    @PostMapping("/update")
    public String update(Prodotti prodotti) {
        r.save(prodotti);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(Long id){
        r.deleteById(id);
        return "redirect:/";
    }
}
