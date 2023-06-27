package com.example.mp5.repository;

import com.example.mp5.model.gardens.PublicGarden;
import com.example.mp5.model.gardens.RoyalGarden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoyalGardenRepository extends JpaRepository<RoyalGarden, Long> {

    @Query("SELECT g FROM RoyalGarden g WHERE g.castle.id = :castleId")
    List<RoyalGarden> findByCastleId(@Param("castleId") Long castleId);
}