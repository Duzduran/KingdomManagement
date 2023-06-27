package com.example.mp5.repository;

import com.example.mp5.model.defenders.Archer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArcherRepository extends DefenderRepository {
    List<Archer> findArcherByHealthGreaterThan(int health);

    @Query("SELECT a FROM Archer a WHERE a.defends.id = :castleId")
    List<Archer> findByCastleId(@Param("castleId") Long castleId);

}
