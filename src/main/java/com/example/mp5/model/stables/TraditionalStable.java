package com.example.mp5.model.stables;

import com.example.mp5.model.util.interfaces.ITraditionalStable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class TraditionalStable extends Stable implements ITraditionalStable {
    @NotNull
    private boolean hasHay;
    private int numOfJockeys;
    private Set<String> horseNames = new HashSet<>(); //  { "Thunderbolt", "Midnight Star", "Rapid Fire", "Golden Arrow", "Silver Blaze" };

    public int calculateNumOfRaces() {
        int numOfHorses = getNumOfHorses();
        int numOfRaces = numOfHorses / numOfJockeys; // Each jockey handles multiple horses

        return numOfRaces;
    }

    public double calculateAverageSpeed() {
        int totalSpeed = 0;
        int numOfHorses = getNumOfHorses();

        for (int i = 0; i < numOfHorses; i++) {
            int horseSpeed = (int) (Math.random() * 30 + 50); // Generate a random speed between 50 and 80 km/h
            totalSpeed += horseSpeed;
        }

        double averageSpeed = (double) totalSpeed / numOfHorses;
        return Math.round(averageSpeed * 100.0) / 100.0; // Round the average speed to two decimal places
    }

    public String determineWinningHorse() {
        int numOfHorses = getNumOfHorses();
        if (horseNames.size() < numOfHorses) {
            generateUniqueHorseNames(numOfHorses);
        }

        int randomIndex = (int) (Math.random() * numOfHorses);
        String[] horseNamesArray = horseNames.toArray(new String[0]);

        return horseNamesArray[randomIndex];
    }

    private void generateUniqueHorseNames(int numOfHorses) {
        int remainingNames = numOfHorses - horseNames.size();
        String[] additionalHorseNames = { "Starlight", "Silver Bullet", "Firefly", "Golden Hooves", "Swiftwind" };

        while (remainingNames > 0) {
            int randomIndex = (int) (Math.random() * additionalHorseNames.length);
            String horseName = additionalHorseNames[randomIndex];

            if (!horseNames.contains(horseName)) {
                horseNames.add(horseName);
                remainingNames--;
            }
        }
    }

    @Override
    public void organizeRaces() {
        int numOfRaces = calculateNumOfRaces();
        double averageSpeed = calculateAverageSpeed();
        String winningHorse = determineWinningHorse();

        System.out.println("Organizing races in the traditional stable: " + getName());
        System.out.println("Number of races: " + numOfRaces);
        System.out.println("Average speed of the horses: " + averageSpeed + " km/h");
        System.out.println("The winning horse is: " + winningHorse);
    }

}