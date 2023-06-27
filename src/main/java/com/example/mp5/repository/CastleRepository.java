package com.example.mp5.repository;

import com.example.mp5.model.Castle;
import com.example.mp5.model.util.enums.Religion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CastleRepository extends JpaRepository<Castle, Long> {
    public List<Castle> findByName(String name);


    @Query("SELECT c FROM Castle c WHERE c.religion = :religion")
    List<Castle> findCastlesByReligion(Religion religion);

    @Query("from Castle as c left join fetch c.defenders where c.id = :id")
    public Optional<Castle> findById(@Param("id") Long id);
}
