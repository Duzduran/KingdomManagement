package com.example.mp5.repository;

import com.example.mp5.model.gardens.PublicGarden;
import com.example.mp5.model.stables.PrivateStable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PublicGardenRepository extends JpaRepository<PublicGarden, Long> {

    @Query("SELECT g FROM PublicGarden g WHERE g.castle.id = :castleId")
    List<PublicGarden> findByCastleId(@Param("castleId") Long castleId);
}