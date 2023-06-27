package com.example.mp5.repository;

import com.example.mp5.model.GuardDuty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuardDutyRepository extends JpaRepository<GuardDuty, Long> {
}