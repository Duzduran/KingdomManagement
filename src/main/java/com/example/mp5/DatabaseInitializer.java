package com.example.mp5;

import com.example.mp5.model.*;
import com.example.mp5.model.defenders.Archer;
import com.example.mp5.model.defenders.Defender;
import com.example.mp5.model.defenders.GateHolder;
import com.example.mp5.model.stables.PrivateStable;
import com.example.mp5.model.stables.TraditionalStable;
import com.example.mp5.model.stables.Governor;
import com.example.mp5.model.util.enums.*;
import com.example.mp5.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Configuration class for initializing the database.
 */
@Configuration
public class DatabaseInitializer {
    private final BalistariaRepository balistariaRepository;

    private final GuardDutyRepository guardDutyRepository;

    private final CastleRepository castleRepository;
    private final ArcherRepository archerRepository;
    private final GateHolderRepository gateHolderRepository;
    private final RoyalStableRepository royalStableRepository;
    private final TraditionalStableRepository traditionalStableRepository;
    private final ChapelRepository chapelRepository;
    private final GovernorRepository governorRepository;
    private final PrivateStableRepository privateStableRepository;

    public DatabaseInitializer(BalistariaRepository balistariaRepository, GuardDutyRepository guardDutyRepository, CastleRepository castleRepository, ArcherRepository archerRepository, GateHolderRepository gateHolderRepository, DefenderRepository defenderRepository, RoyalStableRepository royalStableRepository,
                               TraditionalStableRepository traditionalStableRepository,
                               ChapelRepository chapelRepository,
                               GovernorRepository governorRepository,
                               PrivateStableRepository privateStableRepository) {
        this.balistariaRepository = balistariaRepository;
        this.guardDutyRepository = guardDutyRepository;
        this.castleRepository = castleRepository;
        this.archerRepository = archerRepository;
        this.gateHolderRepository = gateHolderRepository;
        this.royalStableRepository = royalStableRepository;
        this.traditionalStableRepository = traditionalStableRepository;
        this.chapelRepository = chapelRepository;
        this.governorRepository = governorRepository;
        this.privateStableRepository = privateStableRepository;
    }
    /**
     * Initializes the database with sample data.
     *
     * @return A CommandLineRunner bean that populates the database with initial data.
     */
    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            Set<Defender> defenders1 = new HashSet<>();
            Set<GuardDuty> guardDuties1 = new HashSet<>();
            Set<Chapel> chapels1 = new HashSet<>();
            Set<Village> villages = new HashSet<>();

            Castle castle1 = new Castle();
            castle1.setName("Castle Black");
            castle1.setNumOfTowers(10);
            castle1.setInnerArea(500.0);
            castle1.setBelongedKingdom(Kingdom.Angles);
            castle1.setReligion(Religion.Orthodox);
            castle1.setNearbyCliff(true);
            castle1.setBendOfRiver(false);
            castle1.setNumOfInnerWalls(2);
            castle1.setAmountOfStoredFood(3000.0);
            castle1.setDefenders(defenders1);
            castle1.setChapels(chapels1);

            Castle castle2 = new Castle();
            castle2.setName("Winter Fell");
            castle2.setNumOfTowers(15);
            castle2.setInnerArea(1500.0);
            castle2.setBelongedKingdom(Kingdom.Britons);
            castle2.setReligion(Religion.Catholic);
            castle2.setNearbyCliff(false);
            castle2.setBendOfRiver(true);
            castle2.setNumOfInnerWalls(3);
            castle2.setAmountOfStoredFood(5000.0);
            castleRepository.saveAll(Arrays.asList(castle1, castle2));

            TraditionalStable stable1 = TraditionalStable.builder()
                    .name("oldStable")
                    .numOfHorses(30)
                    .castle(castle1)
                    .hasHay(true)
                    .numOfJockeys(45)
                    .build();

            Set<Governor> governors1 = new HashSet<>();

            com.example.mp5.model.stables.RoyalStable royalStable = com.example.mp5.model.stables.RoyalStable.builder()
                    .name("oldBarn")
                    .numOfTools(20)
                    .numOfHorses(30)
                    .castle(castle1)
                    .build();


            Governor governor = Governor.builder()
                    .age(55)
                    .name("Jack")
                    .title("Prof.")
                    .governingStable(royalStable) // Set the reference to RoyalStable
                    .build();
            governors1.add(governor);
            royalStable.setGovernors(governors1);
            royalStableRepository.save(royalStable);

            governorRepository.save(governor);


            Set<StableRole> roles = new HashSet<>();
            roles.add(StableRole.ROYAL_STABLE);
            PrivateStable stable = PrivateStable.builder()
                    .name("ExpensiveStable")
                    .castle(castle1)
                    .numOfHorses(40)
                    .hasHay(true)
                    .roles(roles)
                    .build();

            privateStableRepository.save(stable);

            Set<ArrowType> arrowTypes1 = new HashSet<>();
            arrowTypes1.add(ArrowType.ICE);

            Set<PrimaryWeapon> weapons = new HashSet<>();
            weapons.add(PrimaryWeapon.SPEAR);

            Archer archer1 = Archer.builder()
                    .name("Archer1")
                    .health(300)
                    .damage(400)
                    .defends(castle1)
                    .arrowTypes(arrowTypes1)
                    .numOfArrows(20)
                    .guardDuties(new HashSet<>()) // Initialize the guardDuties field
                    .build();

            Archer archer2 = Archer.builder()
                    .name("Archer2")
                    .health(200)
                    .damage(300)
                    .defends(castle1)
                    .arrowTypes(arrowTypes1)
                    .numOfArrows(15)
                    .guardDuties(new HashSet<>()) // Initialize the guardDuties field
                    .build();

            GateHolder holder1 = GateHolder.builder()
                    .name("holder1")
                    .health(350)
                    .damage(300)
                    .stamina(100)
                    .defends(castle1)
                    .weapons(weapons)
                    .build();

            GateHolder holder2 = GateHolder.builder()
                    .name("holder2")
                    .health(350)
                    .damage(300)
                    .stamina(100)
                    .defends(castle2)
                    .weapons(weapons)
                    .build();

            Chapel chapel1 = Chapel.builder()
                    .name("bestChapelEver")
                    .belongedCastle(castle1)
                    .capacity(100)
                    .religion(Religion.Protestan)
                    .isHoldDay(false)
                    .locationInCastle(Areas.EAST_WING)
                    .build();

            chapelRepository.save(chapel1);
            gateHolderRepository.saveAll(Arrays.asList(holder1, holder2));
            archerRepository.saveAll(Arrays.asList(archer1, archer2));

            Balistaria balistaria1 = new Balistaria();
            balistaria1.setName("Altar ve avarileri");
            balistaria1.setNumberOfBolts(20);
            balistaria1.setPower(60);
            balistaria1.setBelongedPlace(castle1);


            balistariaRepository.save(balistaria1);

            villages.add(Village.TIER3_VILLAGE);


            GuardDuty guardDuty1 = new GuardDuty();
            guardDuty1.setName("duty1");
            guardDuty1.setArcher(archer1);
            guardDuty1.setStatus(DutyStatus.GUARDING);
            guardDuty1.setBalistaria(balistaria1);
            villages.add(Village.TIER3_VILLAGE);
            guardDuty1.setVisibleHorizon(villages);
            guardDutyRepository.save(guardDuty1);
            archer1.getGuardDuties().add(guardDuty1);
            balistaria1.getGuardDuties().add(guardDuty1);


            guardDuty1.setVisibleHorizon(villages);
            guardDutyRepository.save(guardDuty1);
            GuardDuty guardDuty2 = new GuardDuty();
            guardDuty2.setName("duty2");
            guardDuty2.setArcher(archer2);
            guardDuty2.setStatus(DutyStatus.GUARDING);

            guardDuty2.setBalistaria(balistaria1);
            guardDuty2.setVisibleHorizon(villages);
            guardDutyRepository.save(guardDuty2);

            archer2.getGuardDuties().add(guardDuty2);
            balistaria1.getGuardDuties().add(guardDuty2);

            // Add guardDuties to archer and balistaria


            royalStableRepository.save(royalStable);
            castleRepository.saveAll(Arrays.asList(castle1, castle2));
            archerRepository.saveAll(Arrays.asList(archer1, archer2));
       };
    };
}