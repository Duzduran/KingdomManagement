package com.example.mp5.repository;

import com.example.mp5.model.stables.RoyalStable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoyalStableRepository extends JpaRepository<RoyalStable, Long> {

    List<com.example.mp5.model.stables.RoyalStable> findByCastle_Id(Long castleId);

}
