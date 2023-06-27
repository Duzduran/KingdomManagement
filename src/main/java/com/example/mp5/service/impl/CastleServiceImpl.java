package com.example.mp5.service.impl;

import com.example.mp5.model.Castle;
import com.example.mp5.repository.CastleRepository;
import com.example.mp5.service.CastleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CastleServiceImpl implements CastleService {
    @Autowired
    private CastleRepository castleRepository;

    @Autowired
    public CastleServiceImpl(CastleRepository castleRepository) {
        this.castleRepository = castleRepository;
    }

    @Override
    public Castle findById(long id) {
        Optional<Castle> optionalCastle = castleRepository.findById(id);
        if (optionalCastle.isEmpty()) {
            throw new IllegalArgumentException("Castle with id " + id + " not found");
        }
        return optionalCastle.get();
    }
    @Override
    public List<Castle> findAll() {
        return (List<Castle>) castleRepository.findAll();
    }
}
