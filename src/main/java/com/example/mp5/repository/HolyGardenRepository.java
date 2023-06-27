package com.example.mp5.repository;

import com.example.mp5.model.defenders.GateHolder;
import com.example.mp5.model.gardens.HolyGarden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HolyGardenRepository extends JpaRepository<HolyGarden, Long> {
    @Query("SELECT g FROM HolyGarden g WHERE g.castle.id = :castleId")
    List<HolyGarden> findByCastleId(@Param("castleId") Long castleId);

}