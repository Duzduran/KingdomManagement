package com.example.mp5.controller;

import com.example.mp5.model.stables.TraditionalStable;
import com.example.mp5.repository.TraditionalStableRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TraditionalStableController {

    private TraditionalStableRepository traditionalStableRepository;


    public TraditionalStableController(TraditionalStableRepository traditionalStableRepository) {
        this.traditionalStableRepository = traditionalStableRepository;
    }

    @GetMapping("/traditionalStable/{id}")
    public String getTraditionalStable(@PathVariable long id, Model model){
        TraditionalStable traditionalStable = traditionalStableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid traditional stable Id:" + id));
        model.addAttribute("traditionalStable", traditionalStable);
        return "traditionalStable";
    }
}
