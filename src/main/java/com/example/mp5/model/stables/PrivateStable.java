package com.example.mp5.model.stables;

import com.example.mp5.model.util.enums.StableRole;
import com.example.mp5.model.util.interfaces.IRoyalStable;
import com.example.mp5.model.util.interfaces.ITraditionalStable;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter

/**
 * Represents a private stable, extending the Stable class and implementing ITraditionalStable and IRoyalStable interfaces.
 */
public class PrivateStable extends Stable implements ITraditionalStable, IRoyalStable {
    /**
     * Indicates whether the private stable has hay.
     */
    private boolean hasHay;
    /**
     * The roles associated with the private stable.
     */
    private Set<StableRole> roles;
    /**
     * Calculates the total horse power of the private stable.
     *
     * @return The total horse power.
     */
    public int calculateTotalHorsePower() {
        int totalHorsePower = getNumOfHorses() * 10;

        if (roles.contains(StableRole.TRADITIONAL_STABLE)) {
            totalHorsePower += 50;
        } else if (roles.contains(StableRole.ROYAL_STABLE)) {
            totalHorsePower += 100;
        }
        return totalHorsePower;
    }
    /**
     * Calculates the race distance of the private stable.
     *
     * @return The race distance in meters.
     */
    public int calculateRaceDistance() {
        int baseDistance = 100; // Base distance for a race in meters
        int additionalDistance = 0;

        if (getNumOfHorses() >= 10) {
            additionalDistance = 200; // Additional distance for larger races
        }

        return baseDistance + additionalDistance;
    }

    /**
     * Calculates the number of races that can be organized in the private stable.
     *
     * @return The number of races.
     */

    public int calculateNumOfRaces() {
        int numOfHorses = getNumOfHorses();
        int numOfRaces = numOfHorses / 5; // 5 horses per race

        return numOfRaces;
    }
    /**
     * Calculates the duration of the parade in the private stable.
     *
     * @return The duration of the parade in minutes.
     */

    public int calculateParadeDuration() {
        int numOfHorses = getNumOfHorses();
        int durationPerHorse = 10; // Duration in minutes per horse

        return numOfHorses * durationPerHorse;
    }
    /**
     * Calculates the number of participants in the private stable.
     *
     * @return The number of participants.
     */

    public int calculateNumOfParticipants() {
        int numOfHorses = getNumOfHorses();
        int numOfParticipants = numOfHorses / 2; // 2 people per horse

        return numOfParticipants;
    }

    /**
     * Organizes races in the private stable if it has the role of a traditional stable.
     * Otherwise, throws an UnsupportedOperationException.
     */
    @Override
    public void organizeRaces() {
        if (roles.contains(StableRole.TRADITIONAL_STABLE)) {
            int raceDistance = calculateRaceDistance();
            int numOfRaces = calculateNumOfRaces();
            int totalDistance = raceDistance * numOfRaces;

            System.out.println("Organizing races in the traditional stable: " + getName());
            System.out.println("Race distance: " + raceDistance);
            System.out.println("Number of races: " + numOfRaces);
            System.out.println("Total distance covered: " + totalDistance);
        } else {
            throw new UnsupportedOperationException("This stable does not have the role of a traditional stable.");
        }
    }

    /**
     * Conducts a royal parade from the private stable if it has the role of a royal stable.
     * Otherwise, throws an UnsupportedOperationException.
     */
    @Override
    public void conductRoyalParade() {
        if (roles.contains(StableRole.ROYAL_STABLE)) {
            int paradeDuration = calculateParadeDuration();
            int numOfParticipants = calculateNumOfParticipants();

            System.out.println("Conducting a royal parade from the royal stable: " + getName());
            System.out.println("Parade duration: " + paradeDuration);
            System.out.println("Number of participants: " + numOfParticipants);
        } else {
            throw new UnsupportedOperationException("This stable does not have the role of a royal stable.");
        }
    }

}