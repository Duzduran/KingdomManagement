package com.example.mp5.service;

import com.example.mp5.model.Chapel;
import com.example.mp5.model.Clergy;

public interface ClergyService {
    public void addClergyToChapel(Clergy clergy, Chapel chapel);

    public void removeClergyFromChapel(Clergy clergy);

    }
