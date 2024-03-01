package com.example.magazzon;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;

@Controller
public class MagazzonProductsController {
    private MagazzonProductsRepository repository;

    public MagazzonProductsController(MagazzonProductsRepository repository){
        this.repository=repository;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("products", repository.findAll());
        return "index";
    }

    @GetMapping("/create")
    public String create(){
        return "create";
    }

    @GetMapping("/store")
    public String store(MagazzonProductsModel products){
        repository.save(products);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(Model model, Long id){
        model.addAttribute("product", repository.findById(id).orElse(null));
        return "redirect:/";
    }

    @GetMapping("/show")
    public String show(Model model, Long id) {
        model.addAttribute("product", repository.findById(id).orElse(null));
        return "show";
    }

    @GetMapping("/update")
    public String update(MagazzonProductsModel products){
        repository.save(products);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(Long id){
        repository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/filter")
    public String filter(Model model, String action) {
        if (action.equals("filter")){
            model.addAttribute("products", repository.findByQuantityGreaterThan(0));
            return "filter";
        } else if (action.equals("unfilter")) {

        }
        return "redirect:/";
    }
}
