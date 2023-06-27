package com.example.mp5.repository;

import com.example.mp5.model.Balistaria;
import com.example.mp5.model.Castle;
import com.example.mp5.model.Clergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClergyRepository extends JpaRepository<Clergy, Long> {

    @Query("SELECT a FROM Clergy a WHERE a.chapel.id = :chapelId")
    List<Clergy> findByChapelLId(@Param("chapelId") Long chapelId);
}

