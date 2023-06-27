package com.example.mp5.repository;

import com.example.mp5.model.Balistaria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalistariaRepository extends CrudRepository<Balistaria, Long> {
    Balistaria findByName(String name);

    List<Balistaria> findByPowerGreaterThan(int power);

    @Query("SELECT a FROM Balistaria a WHERE a.belongedPlace.id = :castleId")
    List<Balistaria> findByCastleId(@Param("castleId") Long castleId);
}
