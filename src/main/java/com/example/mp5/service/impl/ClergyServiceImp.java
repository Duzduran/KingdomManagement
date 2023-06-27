package com.example.mp5.service.impl;
import com.example.mp5.model.Chapel;
import com.example.mp5.model.Clergy;
import com.example.mp5.repository.ChapelRepository;
import com.example.mp5.repository.ClergyRepository;
import com.example.mp5.service.ClergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClergyServiceImp implements ClergyService {
    @Autowired
    private ClergyRepository clergyRepository;
    @Autowired
    private ChapelRepository chapelRepository;

    public void addClergyToChapel(Clergy clergy, Chapel chapel) {
        Chapel existingChapel = clergy.getChapel();
        if (existingChapel != null) {
            existingChapel.getClergies().remove(clergy);
            chapelRepository.save(existingChapel);
        }

        clergy.setChapel(chapel);
        chapel.getClergies().add(clergy);
        clergyRepository.save(clergy);
    }
    public void removeClergyFromChapel(Clergy clergy) {
        Chapel chapel = clergy.getChapel();
        if (chapel != null) {
            chapel.getClergies().remove(clergy);
            clergy.setChapel(null);
            chapelRepository.save(chapel);
            clergyRepository.save(clergy);
        }
    }


    public List<Clergy> getAllClergies() {
        return clergyRepository.findAll();
    }

    public Clergy getClergyById(Long id) {
        return clergyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Clergy not found with id: " + id));
    }

    public Clergy saveClergy(Clergy clergy) {
        return clergyRepository.save(clergy);
    }

    public void deleteClergyById(Long id) {
        if (clergyRepository.existsById(id)) {
            clergyRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Clergy not found with id: " + id);
        }
    }
}
