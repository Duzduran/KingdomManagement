package com.example.mp5.repository;

import com.example.mp5.model.defenders.GateHolder;
import com.example.mp5.model.stables.PrivateStable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrivateStableRepository extends JpaRepository<PrivateStable,Long> {

    @Query("SELECT g FROM PrivateStable g WHERE g.castle.id = :castleId")
    List<PrivateStable> findByCastleId(@Param("castleId") Long castleId);
}
