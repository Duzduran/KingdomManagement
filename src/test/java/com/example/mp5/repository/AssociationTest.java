package com.example.mp5.repository;

import com.example.mp5.model.defenders.Archer;
import com.example.mp5.model.Castle;
import com.example.mp5.model.util.enums.ArrowType;
import com.example.mp5.model.util.enums.Kingdom;
import com.example.mp5.model.util.enums.Religion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AssociationTest
{
    @Autowired
    private DefenderRepository defenderRepository;

    @Autowired
    private CastleRepository castleRepository;

    @Autowired
    ArcherRepository archerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Castle castle1;
    Archer archer1;

    @BeforeEach
    public void initData(){
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

        Set<ArrowType> archer1ArrowTypes = new HashSet<>(Arrays.asList(ArrowType.WOODEN, ArrowType.STEEL));
        archer1 = Archer.builder()
                .name("Archer 1")
                .health(150)
                .damage(20)
                .arrowTypes(archer1ArrowTypes)
                .build();
    }
    @Test
    public void testRequiredDep(){
        assertNotNull(archerRepository);
        assertNotNull(castleRepository);
    }

    @Test
    public void testSave() {
        castle1.getDefenders().add(archer1);
        castleRepository.save(castle1);
        archer1.setDefends(castle1);
        archerRepository.save(archer1);

        Optional<Castle> byId = castleRepository.findById(castle1.getId());
        assertTrue(byId.isPresent());
        System.out.println(byId.get().getDefenders());
        assertEquals(1,byId.get().getDefenders().size());
    }
}
