package com.example.mp5.repository;

import com.example.mp5.model.Castle;
import com.example.mp5.model.util.enums.Kingdom;
import com.example.mp5.model.util.enums.Religion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CastleRepositoryTest {
    @Autowired
    private CastleRepository castleRepository;

    @PersistenceContext
    private EntityManager entityManager;
    Castle castle1;

    @BeforeEach
    public void initData(){
        Castle castle = Castle.builder()
                .name("PORAY")
                .numOfTowers(5)
                .innerArea(275.76)
                .belongedKingdom(Kingdom.Saxons)
                .religion(Religion.Orthodox)
                .nearbyCliff(false)
                .bendOfRiver(false)
                .numOfInnerWalls(3)
                .amountOfStoredFood(400.0)
                .build();
        castle1 = Castle.builder()
                .name("Polonia")
                .numOfTowers(6)
                .innerArea(355.76)
                .belongedKingdom(Kingdom.Commonwealth)
                .religion(Religion.Catholic)
                .nearbyCliff(true)
                .bendOfRiver(false)
                .numOfInnerWalls(4)
                .amountOfStoredFood(480.7)
                .build();

        castleRepository.save(castle);
    }


    @Test
    public void TestRequiredDependencies(){
        assertNotNull(castleRepository);
    }

    @Test
    public void testFetchCastles(){
        Iterable<Castle> all = castleRepository.findAll();
        for(Castle d : all)
            System.out.println(d);
    }
    @Test
    public void testSaveDepartment() {
        castleRepository.save(castle1);
        entityManager.flush();
        long count = castleRepository.count();
        assertEquals(2, count);
    }
    @Test
    public void testInvalidNumOfTowers() {
        assertThrows(ConstraintViolationException.class, () -> {
            Castle castle = Castle.builder()
                    .name("Invalid Castle")
                    .numOfTowers(2)
                    .innerArea(200.0)
                    .belongedKingdom(Kingdom.Saxons)
                    .religion(Religion.Protestan)
                    .nearbyCliff(false)
                    .bendOfRiver(false)
                    .numOfInnerWalls(2)
                    .amountOfStoredFood(300.0)
                    .build();
            castleRepository.save(castle);
            entityManager.flush();
        });
    }

    @Test
    public void testFindByName(){
        List<Castle> castles = castleRepository.findByName("PORAY");
        assertEquals(1,castles.size());
    }


    @Test
    public void testFindCastlesByReligion() {
        // Persist some castles in the test database
        Castle castle1 = Castle.builder()
                .name("Castle 1")
                .numOfTowers(5)
                .innerArea(200.0)
                .belongedKingdom(Kingdom.Angles)
                .religion(Religion.Catholic)
                .nearbyCliff(false)
                .bendOfRiver(false)
                .numOfInnerWalls(2)
                .amountOfStoredFood(300.0)
                .build();
        entityManager.persist(castle1);

        Castle castle2 = Castle.builder()
                .name("Castle 2")
                .numOfTowers(5)
                .innerArea(250.0)
                .belongedKingdom(Kingdom.Britons)
                .religion(Religion.Orthodox)
                .nearbyCliff(true)
                .bendOfRiver(false)
                .numOfInnerWalls(1)
                .amountOfStoredFood(250.0)
                .build();
        entityManager.persist(castle2);

        // Invoke the query method
        List<Castle> catholicCastles = castleRepository.findCastlesByReligion(Religion.Catholic);

        // Assertions
        Assertions.assertEquals(1, catholicCastles.size());
        Assertions.assertEquals("Castle 1", catholicCastles.get(0).getName());
    }
}