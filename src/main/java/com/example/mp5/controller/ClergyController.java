package com.example.mp5.controller;


import com.example.mp5.model.Balistaria;
import com.example.mp5.model.Clergy;
import com.example.mp5.repository.BalistariaRepository;
import com.example.mp5.repository.ClergyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClergyController {

    private final ClergyRepository clergyRepository;

    public ClergyController(ClergyRepository clergyRepository) {
        this.clergyRepository = clergyRepository;
    }

    @GetMapping("/clergy/{id}")
    public String getBalistaria(@PathVariable Long id, Model model) {
        Clergy clergy = clergyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid clergy Id:" + id));
        model.addAttribute("clergy", clergy);
        return "clergy";
    }
}

