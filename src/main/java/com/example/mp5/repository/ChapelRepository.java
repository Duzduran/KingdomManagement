package com.example.mp5.repository;

import com.example.mp5.model.Chapel;
import com.example.mp5.model.util.enums.Religion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChapelRepository extends JpaRepository<Chapel,Long> {
    Chapel findByName(String name);
    @Query("SELECT a FROM Chapel a WHERE a.belongedCastle.id = :castleId")
    List<Chapel> findByCastleId(@Param("castleId") Long castleId);


    @Query("from Chapel as c left join fetch c.clergies where c.id = :id")
    public Optional<Chapel> findById(@Param("id") Long id);
    List<Chapel> findByReligion(Religion  religion);
}
