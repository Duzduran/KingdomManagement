package com.example.mp5.model.gardens;

import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.DiscriminatorValue;
import lombok.experimental.SuperBuilder;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class HolyGarden extends Garden {

    private boolean isBlessed;

    public HolyGarden(Garden garden) {
        super(garden);
    }
    @Override
    public int water() {
        if (isBlessed) {
            return getNumberOfPlants() * 2 + getYearsOld() * 10;
        } else {
            return -1; // Error code when garden is not blessed
        }
    }

    @Override
    public int plant(String plantType) {
        if (isSpaceAvailable()) {
            return getNumberOfPlants() + 2 + getSize() * getYearsOld();
        } else {
            return -1; // Error code when no space is available
        }
    }

    @Override
    public int calculateTotalLifeTime() {
        return getYearsOld() * getSize() * 2 - getNumberOfPlants() * 5;
    }
}