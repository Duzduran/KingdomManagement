package com.example.mp5.controller;

import com.example.mp5.model.Castle;
import com.example.mp5.model.util.enums.Kingdom;
import com.example.mp5.model.util.enums.Religion;
import com.example.mp5.repository.CastleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CastleRepository castleRepository;


    @GetMapping("/home")
    public String home(@RequestParam(required = false) Religion religion, Model model) {
        List<Castle> castles;
        if (religion != null) {
            castles = castleRepository.findCastlesByReligion(religion);
        } else {
            castles = castleRepository.findAll();
        }

        model.addAttribute("castles", castles);

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        model.addAttribute("currentTime", formattedTime);
        model.addAttribute("religions", Religion.values());
        return "home";
    }

    @GetMapping("/castlesByReligion")
    public ResponseEntity<List<Castle>> getCastlesByReligion(@RequestParam Religion religion) {
        List<Castle> castles = castleRepository.findCastlesByReligion(religion);
        return ResponseEntity.ok(castles);
    }

    @GetMapping("/castle/new")
    public String getNewCastleForm(Model model) {
        model.addAttribute("castle", new Castle());
        model.addAttribute("religions", Religion.values());
        model.addAttribute("kingdoms", Kingdom.values());
        return "newCastle";
    }

    @PostMapping("/castle/new")
    public String createNewCastle(@ModelAttribute Castle castle) {
        castleRepository.save(castle);
        return "redirect:/home";
    }


}