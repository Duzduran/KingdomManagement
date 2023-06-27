package com.example.mp5.controller;

import com.example.mp5.model.defenders.GateHolder;
import com.example.mp5.repository.GateHolderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class GateHolderController {

    @Autowired
    private GateHolderRepository gateHolderRepository;

    @GetMapping("/gateHolder/{id}")
    public String getGateHolder(@PathVariable Long id, Model model) {
        GateHolder gateHolder = (GateHolder) gateHolderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid gateHolder Id:" + id));
        model.addAttribute("gateHolder", gateHolder);
        return "gateHolderDetails";
    }
}