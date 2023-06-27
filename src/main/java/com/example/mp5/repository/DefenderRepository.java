package com.example.mp5.repository;

import com.example.mp5.model.defenders.Defender;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefenderRepository extends CrudRepository<Defender, Long> {

}
