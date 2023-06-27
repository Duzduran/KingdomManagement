package com.example.mp5.repository;

import com.example.mp5.model.Balistaria;
import com.example.mp5.model.Castle;
import com.example.mp5.model.util.enums.Kingdom;
import com.example.mp5.model.util.enums.Religion;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class BalistariaRepositoryTest {

    @Autowired
    private BalistariaRepository balistariaRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CastleRepository castleRepository;
    Castle castle;
    @BeforeEach
    public void setUp() {
        // Create and persist sample data before each test
        castle = Castle.builder()
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

        Balistaria balistaria1 = Balistaria.builder()
                .name("Balistaria 1")
                .numberOfBolts(10)
                .power(50)
                .belongedPlace(castle)
                .build();
        entityManager.persistAndFlush(balistaria1);

        Balistaria balistaria2 = Balistaria.builder()
                .name("Balistaria 2")
                .numberOfBolts(15)
                .power(60)
                .belongedPlace(castle)
                .build();
        entityManager.persistAndFlush(balistaria2);
    }

    @Test
    public void testFindByName() {
        Balistaria foundBalistaria = balistariaRepository.findByName("Balistaria 1");
        assertNotNull(foundBalistaria);
        assertEquals("Balistaria 1", foundBalistaria.getName());
    }

    @Test
    public void testFindByPowerGreaterThan() {
        List<Balistaria> balistariasByPower = balistariaRepository.findByPowerGreaterThan(55);
        assertNotNull(balistariasByPower);
        assertEquals(1, balistariasByPower.size());
        assertTrue(balistariasByPower.get(0).getPower() > 55);
    }
}