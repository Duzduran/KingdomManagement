package com.example.mp5.controller;

import com.example.mp5.model.stables.PrivateStable;
import com.example.mp5.repository.PrivateStableRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PrivateStableController {

    private PrivateStableRepository privateStableRepository;

    public PrivateStableController(PrivateStableRepository privateStableRepository) {
        this.privateStableRepository = privateStableRepository;
    }

    @GetMapping("/privateStable/{id}")
    public String getPrivateStable(@PathVariable long id, Model model) {
        PrivateStable privateStable = privateStableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid privateStable Id:" + id));

        model.addAttribute("privateStable", privateStable);
        return "privateStable";
    }
}