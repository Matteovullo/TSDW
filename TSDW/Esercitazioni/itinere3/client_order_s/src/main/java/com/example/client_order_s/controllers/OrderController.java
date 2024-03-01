package com.example.client_order_s.controllers;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.*;

import com.example.client_order_s.models.Order;
import com.example.client_order_s.repositories.ClientRepository;
import com.example.client_order_s.repositories.OrderRepository;

@Controller
public class OrderController {
    
    private ClientRepository repoClient;
    private OrderRepository repo;

    public OrderController(ClientRepository repoClient, OrderRepository repo){
        this.repo=repo;
        this.repoClient=repoClient;
    }

    @GetMapping("/orders")
    public String index(Model model){
        model.addAttribute("orders", repo.findAll());
        return "orders/index";
    }

    @GetMapping("/orders/show")
    public String show(Model model, Long id){
        model.addAttribute("order", repo.findById(id).orElse(null));
        model.addAttribute("client", repoClient.findAll());
        return "orders/show";
    }

    @GetMapping("/orders/create")
    public String crete(Model model){
        model.addAttribute("clients", repoClient.findAll());
        return "orders/create";
    }

    @GetMapping("/orders/edit")
    String edit(Long id, Model model){
        model.addAttribute("order", repo.findById(id).orElse(null));
        model.addAttribute("clients", repoClient.findAll());
        return "/orders/edit";
    }

    @PostMapping("/orders/store")
    public String store(Order order){
        repo.save(order);
        return "redirect:/orders";
    }

    @PostMapping("/orders/update")
    public String update(Order order){
        repo.save(order);
        return "redirect:/orders";
    }

    @PostMapping("/orders/delete")
    public String delete(Long id){
        repo.deleteById(id);
        return "redirect:/orders";
    }
    
}
