package com.example.mp5.repository;

import com.example.mp5.model.stables.TraditionalStable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraditionalStableRepository extends JpaRepository<TraditionalStable, Long> {

    List<TraditionalStable> findByCastle_Id(Long castleId);

}
