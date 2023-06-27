package com.example.mp5.repository;

import com.example.mp5.model.Castle;
import com.example.mp5.model.Chapel;
import com.example.mp5.model.util.enums.Areas;
import com.example.mp5.model.util.enums.Kingdom;
import com.example.mp5.model.util.enums.Religion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ChapelRepositoryTest {
    @Autowired
    private ChapelRepository chapelRepository;
    @Autowired
    private CastleRepository castleRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void setUp() {
        Castle polishCastle = Castle.builder()
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

        castleRepository.save(polishCastle);
        Chapel chapel1 = Chapel.builder()
                .name("Chapel 1")
                .locationInCastle(Areas.SOUTHWEST_CORNER)
                .capacity(100)
                .isHoldDay(true)
                .religion(Religion.Catholic)
                .belongedCastle(polishCastle)
                .build();
        entityManager.persistAndFlush(chapel1);

        Chapel chapel2 = Chapel.builder()
                .name("Chapel 2")
                .locationInCastle(Areas.CENTER)
                .capacity(200)
                .isHoldDay(false)
                .religion(Religion.Protestan)
                .belongedCastle(polishCastle)
                .build();
        entityManager.persistAndFlush(chapel2);
        chapelRepository.save(chapel1);
        chapelRepository.save(chapel2);

    }

    @Test
    public void testFindByName() {
        Chapel foundChapel = chapelRepository.findByName("Chapel 1");
        assertNotNull(foundChapel);
        assertEquals("Chapel 1", foundChapel.getName());
    }

    @Test
    public void testFindByReligion() {
            List<Chapel> chapelsByReligion = chapelRepository.findByReligion(Religion.Catholic);
        assertNotNull(chapelsByReligion);
        assertEquals(1, chapelsByReligion.size());
        assertEquals(Religion.Catholic, chapelsByReligion.get(0).getReligion());
    }
}