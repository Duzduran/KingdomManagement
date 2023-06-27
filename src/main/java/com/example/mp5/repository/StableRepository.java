package com.example.mp5.repository;

import com.example.mp5.model.stables.Stable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StableRepository extends JpaRepository<Stable, Long> {

    @Query("SELECT a FROM Stable a WHERE a.castle.id = :castleId")
    List<Stable> findByCastleId(@Param("castleId") Long castleId);
}