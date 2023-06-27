package com.example.mp5.controller;

import com.example.mp5.model.Chapel;
import com.example.mp5.model.Clergy;
import com.example.mp5.model.util.enums.Religion;
import com.example.mp5.repository.ChapelRepository;
import com.example.mp5.repository.ClergyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ChapelController {
    private final ChapelRepository chapelRepository;
    private final ClergyRepository clergyRepository;


    @Autowired
    public ChapelController(ChapelRepository chapelRepository,
                            ClergyRepository clergyRepository) {
        this.chapelRepository = chapelRepository;
        this.clergyRepository = clergyRepository;
    }

    @GetMapping("/chapel/{id}")
    public String getChapel(@PathVariable Long id, Model model) throws JsonProcessingException {
        Chapel chapel
                = chapelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid chapel Id:" + id));
        model.addAttribute("chapel", chapel);
        List<Clergy> clergies = clergyRepository.findByChapelLId(id);
        model.addAttribute("clergiesJson", new ObjectMapper().writeValueAsString(clergies));
        model.addAttribute("clergy", new Clergy());
        model.addAttribute("religions", Religion.values());



        return "chapel";
    }

    @PostMapping("/addClergy")
    public String addClergy(@RequestParam Long chapelId, @ModelAttribute Clergy clergy) {
        Optional<Chapel> chapel = chapelRepository.findById(chapelId);
        if (chapel.isEmpty()) {
            // Handle the case where no Castle was found with the provided id
            return "redirect:/error";
        }

        // Add the royalStable to the castle's list of stables
        clergy.setChapel(chapel.get());

        // Save the castle with the new royalStable to the database
        clergyRepository.save(clergy);

        return "redirect:/chapel/" + chapelId;
    }
}
