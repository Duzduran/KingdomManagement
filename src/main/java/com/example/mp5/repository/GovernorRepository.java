package com.example.mp5.repository;

import com.example.mp5.model.stables.Governor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GovernorRepository extends JpaRepository<Governor, Long> {

}
