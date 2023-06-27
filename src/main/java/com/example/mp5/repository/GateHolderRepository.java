package com.example.mp5.repository;

import com.example.mp5.model.defenders.GateHolder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GateHolderRepository extends DefenderRepository{

    List<GateHolder> findGateHoldersByDamageGreaterThan(int a);

    @Query("SELECT g FROM GateHolder g WHERE g.defends.id = :castleId")
    List<GateHolder> findByCastleId(@Param("castleId") Long castleId);

}


