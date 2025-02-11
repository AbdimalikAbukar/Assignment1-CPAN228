package com.example.Assignment1_CPAN228.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Assignment1_CPAN228.data.ItemRepository;
import com.example.Assignment1_CPAN228.model.Fashion_Brands;
import com.example.Assignment1_CPAN228.model.Item;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/design")
public class DesignController {
    private final ItemRepository itemRepo;

    @Autowired
    public DesignController(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    @ModelAttribute
    public void addFashionBrands(Model model) {
        var fashion_brands = List.of(Fashion_Brands.values()).stream().map(Fashion_Brands::name)
                .collect(Collectors.toList());
        model.addAttribute("fashion_brands", fashion_brands);
        log.info("Fashion Brands added to model");
    }

    @ModelAttribute(name = "item")
    public Item item() {
        return new Item(3333L, null, null, 0, null, null);
    }

    @GetMapping
    public String showDesign(Model model) {
        Iterable<Item> item = itemRepo.findAll();
        model.addAttribute("item", item);
        return "design";
    }

    @PostMapping
    public String processDesign(@ModelAttribute Item item) {
        itemRepo.save(item);
        log.info("Processing design: " + item);
        return "redirect:/design";
    }

    @GetMapping("/inventory")
    public String showInventory(Model model) {
        Iterable<Item> item = itemRepo.findAll();
        model.addAttribute("item", item);
        return "inventory";
    }

    @GetMapping("/{id}")
    public String getItemById(@PathVariable Long id, Model model) {
        Optional<Item> item = itemRepo.findById(id);
        if (item.isPresent()) {
            model.addAttribute("item", item.get());
            return "detail";
        }
        return "redirect:/design";
    }

}
