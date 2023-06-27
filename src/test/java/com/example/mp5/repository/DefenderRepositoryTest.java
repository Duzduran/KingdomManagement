package com.example.mp5.repository;

import com.example.mp5.model.defenders.Archer;
import com.example.mp5.model.defenders.GateHolder;
import com.example.mp5.model.util.enums.ArrowType;
import com.example.mp5.model.util.enums.PrimaryWeapon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class DefenderRepositoryTest {
    @Autowired
    private ArcherRepository archerRepository;
    @Autowired
    private GateHolderRepository gateHolderRepository;
    @Autowired DefenderRepository defenderRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Archer archer1, archer2;
    GateHolder holder1, holder2;

    @BeforeEach
    public void setUp() {
        // Create and persist sample data before each test
        Set<ArrowType> archer1ArrowTypes = new HashSet<>(Arrays.asList(ArrowType.WOODEN, ArrowType.STEEL));
        archer1 = Archer.builder()
                .name("Archer 1")
                .health(150)
                .damage(20)
                .arrowTypes(archer1ArrowTypes)
                .build();

        Set<ArrowType> archer2ArrowTypes = new HashSet<>(Arrays.asList(ArrowType.FIRE, ArrowType.ICE));
        archer2 = Archer.builder()
                .name("Archer 2")
                .health(120)
                .damage(25)
                .arrowTypes(archer2ArrowTypes)
                .build();

        Set<PrimaryWeapon> holder1Weapons = new HashSet<>(Arrays.asList(PrimaryWeapon.AXE, PrimaryWeapon.SPEAR));

        holder1 = GateHolder.builder()
                .name("Warrior 1")
                .health(150)
                .damage(30)
                .weapons(holder1Weapons)
                .build();


        Set<PrimaryWeapon> holder2Weapons = new HashSet<>(Arrays.asList(PrimaryWeapon.KATANA, PrimaryWeapon.SWORD));
        holder2 = GateHolder.builder()
                .name("Warrior 2")
                .health(180)
                .damage(35)
                .weapons(holder2Weapons)
                .build();
        archerRepository.saveAll(Arrays.asList(archer1,archer2));
        gateHolderRepository.saveAll(Arrays.asList(holder1,holder2));

    }
    @Test
    public void saveAll(){
        entityManager.flush();
        assertEquals(2,archerRepository.count());
        assertEquals(2,gateHolderRepository.count());

    }

    @Test
    public void testRequiredDependencies() {
        assertNotNull(archerRepository);
        assertNotNull(gateHolderRepository);
        assertNotNull(defenderRepository);
    }

    @Test
    public void testFindArchersByHealthGreaterThan() {
        // Retrieve Archers by health greater than 100
        List<Archer> archersWithHighHealth = archerRepository.findArcherByHealthGreaterThan(100);

        // Assertions
        Assertions.assertEquals(2, archersWithHighHealth.size());
        Assertions.assertEquals("Archer 1", archersWithHighHealth.get(0).getName());
    }

    @Test
    public void testFindGateHoldersByDamageGreaterThan() {
        // Retrieve GateHolders by damage greater than 30
        List<GateHolder> gateHoldersWithHighDamage = gateHolderRepository.findGateHoldersByDamageGreaterThan(30);

        // Assertions
        Assertions.assertEquals(1, gateHoldersWithHighDamage.size());
        Assertions.assertEquals("Warrior 2", gateHoldersWithHighDamage.get(0).getName());
    }
}