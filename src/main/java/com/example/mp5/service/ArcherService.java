package com.example.mp5.service;

import com.example.mp5.model.defenders.Archer;
import com.example.mp5.model.Balistaria;
import com.example.mp5.model.GuardDuty;
import com.example.mp5.repository.ArcherRepository;
import com.example.mp5.repository.BalistariaRepository;
import com.example.mp5.repository.GuardDutyRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service

public class ArcherService {

    private final ArcherRepository archerRepository;

    private final BalistariaRepository balistariaRepository;

    private final GuardDutyRepository guardDutyRepository;

    public ArcherService(ArcherRepository archerRepository, BalistariaRepository balistariaRepository, GuardDutyRepository guardDutyRepository) {
        this.archerRepository = archerRepository;
        this.balistariaRepository = balistariaRepository;
        this.guardDutyRepository = guardDutyRepository;
    }

    public void leaveCastle(Archer archer) {
        Set<GuardDuty> guardDuties = archer.getGuardDuties();

        // Remove the association between GuardDuties and Balistaria
        for (GuardDuty guardDuty : guardDuties) {
            Balistaria balistaria = guardDuty.getBalistaria();
            balistaria.getGuardDuties().remove(guardDuty);
            guardDuty.setBalistaria(null);
            guardDuty.setArcher(null);
            guardDutyRepository.delete(guardDuty);
        }

        // Clear the GuardDuties set
        archer.getGuardDuties().clear();

        // Nullify the castle association
        archer.setDefends(null);


        // Update the archer status to left castle
        // archer.setStatus("left the castle");
        archerRepository.save(archer);


    }
}
