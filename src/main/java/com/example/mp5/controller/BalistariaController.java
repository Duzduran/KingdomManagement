package com.example.mp5.controller;

import com.example.mp5.model.Balistaria;
import com.example.mp5.model.Castle;
import com.example.mp5.repository.BalistariaRepository;
import com.example.mp5.repository.CastleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class BalistariaController {
    private final BalistariaRepository balistariaRepository;

    public BalistariaController(BalistariaRepository balistariaRepository) {
        this.balistariaRepository = balistariaRepository;
    }

    @GetMapping("/balistaria/{id}")
    public String getBalistaria(@PathVariable Long id, Model model) {
        Balistaria balistaria = balistariaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid balistaria Id:" + id));
        model.addAttribute("balistaria", balistaria);
        return "balistaria";
    }
}
