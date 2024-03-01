package com.example.client_order_s.controllers;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.*;

import com.example.client_order_s.models.Client;
import com.example.client_order_s.repositories.ClientRepository;

@Controller
public class ClientController {
    
    private ClientRepository repo;

    public ClientController(ClientRepository repo){
        this.repo=repo;
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/clients")
    public String index(Model model){
        model.addAttribute("clients", repo.findAll());
        return "clients/index";
    }

    @GetMapping("/clients/create")
    public String crete(){
        return "clients/create";
    }

    @GetMapping("/clients/edit")
    String edit(Long id, Model model){
        model.addAttribute("client", repo.findById(id).orElse(null));
        return "/clients/edit";
    }

    @GetMapping("/clients/show")
    String show(Long id, Model model){
        model.addAttribute("client", repo.findById(id).orElse(null));
        return "/clients/show";
    }

    @PostMapping("/clients/store")
    public String store(Client client){
        repo.save(client);
        return "redirect:/clients";
    }

    @PostMapping("/clients/update")
    public String update(Client client){
        repo.save(client);
        return "redirect:/clients";
    }

    @PostMapping("/clients/delete")
    public String delete(Long id){
        repo.deleteById(id);
        return "redirect:/clients";
    }
    
}
