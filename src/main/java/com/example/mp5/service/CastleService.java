package com.example.mp5.service;


import com.example.mp5.model.Castle;

import java.util.List;

public interface CastleService {
    Castle findById(long id);

    public List<Castle> findAll();
}
