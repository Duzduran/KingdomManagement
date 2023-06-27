package com.example.mp5.controller;

import com.example.mp5.model.stables.Governor;
import com.example.mp5.model.stables.RoyalStable;
import com.example.mp5.repository.RoyalStableRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class RoyalStableController {

    private RoyalStableRepository royalStableRepository;


    public RoyalStableController(RoyalStableRepository royalStableRepository) {
        this.royalStableRepository = royalStableRepository;
    }

    @GetMapping("/barn/{id}")
    public String getTraditionalStable(@PathVariable long id, Model model){
        RoyalStable royalStable = royalStableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid royalStable Id:" + id));

        Set<Governor> governors = royalStable.getGovernors();
        String governorNames = governors.stream()
                .map(Governor::getName)
                .collect(Collectors.joining(", "));
        model.addAttribute("royalStable", royalStable);
        model.addAttribute("governorNames", governorNames);

        return "royalStable";
    }
}
