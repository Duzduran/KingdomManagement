package com.example.mp5.controller;

import com.example.mp5.model.defenders.Archer;
import com.example.mp5.model.Castle;
import com.example.mp5.model.GuardDuty;
import com.example.mp5.model.util.enums.DutyStatus;
import com.example.mp5.repository.ArcherRepository;
import com.example.mp5.repository.CastleRepository;
import com.example.mp5.repository.GuardDutyRepository;
import com.example.mp5.service.ArcherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Controller
public class ArcherController {

    private ArcherRepository archerRepository;
    private ArcherService archerService;
    private GuardDutyRepository guardDutyRepository;
    private CastleRepository castleRepository;

    @Autowired
    public ArcherController(GuardDutyRepository guardDutyRepository, ArcherRepository archerRepository, ArcherService archerService, CastleRepository castleRepository){
        this.archerRepository = archerRepository;
        this.guardDutyRepository = guardDutyRepository;
        this.archerService =  archerService;
        this.castleRepository = castleRepository;
    }


    @GetMapping("/archer/{id}")
    public String getArcher(@PathVariable Long id, Model model) {
        Archer archer = (Archer) archerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid archer Id:" + id));
        List<Castle> castles = castleRepository.findAll();


        model.addAttribute("archer", archer);
        model.addAttribute("guardDuties", archer.getGuardDuties());
        model.addAttribute("castles", castles);

        return "archerDetails";
    }

    @GetMapping("/guardDuty/{id}")
    @ResponseBody
    public ResponseEntity<GuardDuty> getGuardDuty(@PathVariable Long id) {
        GuardDuty guardDuty = guardDutyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid guardDuty Id:" + id));

        return ResponseEntity.ok(guardDuty);
    }

    @PutMapping("/guardDuty/{id}/status")
    public ResponseEntity<GuardDuty> updateGuardDutyStatus(@PathVariable Long id) {
        GuardDuty guardDuty = guardDutyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid guardDuty Id:" + id));

        if (guardDuty.getStatus() == DutyStatus.GUARDING) {
            guardDuty.setStatus(DutyStatus.IDLE);
        } else {
            guardDuty.setStatus(DutyStatus.GUARDING);
        }

        guardDutyRepository.save(guardDuty);

        return ResponseEntity.ok(guardDuty);
    }
    @DeleteMapping("/archer/{id}/leaveCastle")
    @ResponseBody
    public ResponseEntity<String> leaveCastle(@PathVariable Long id) {
        Archer archer = (Archer) archerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid archer Id:" + id));

        archerService.leaveCastle(archer);

        return ResponseEntity.ok("Archer has left the castle");
    }


    @PutMapping("/archer/{archerId}/assignCastle/{castleId}")
    @ResponseBody
    public ResponseEntity<String> assignArcherToCastle(@PathVariable Long archerId, @PathVariable Long castleId) {
        Archer archer = (Archer) archerRepository.findById(archerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid archer ID: " + archerId));

        Castle castle = castleRepository.findById(castleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid castle ID: " + castleId));

        archer.setDefends(castle);
        archerRepository.save(archer);

        return ResponseEntity.ok("Archer has been assigned to the castle");
    }

    @PostMapping("/archer/{archerId}/assignToCastle/{castleId}")
    @ResponseBody
    public ResponseEntity<String> assignToCastle(@PathVariable Long archerId, @PathVariable Long castleId) {
        Archer archer = (Archer) archerRepository.findById(archerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid archer Id:" + archerId));
        Castle castle = castleRepository.findById(castleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid castle Id:" + castleId));

        archer.setDefends(castle);
        archerRepository.save(archer);

        return ResponseEntity.ok("Archer assigned to castle successfully");
    }
}