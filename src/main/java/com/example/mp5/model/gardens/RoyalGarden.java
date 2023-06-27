package com.example.mp5.model.gardens;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.DiscriminatorValue;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class RoyalGarden extends Garden {

    private boolean royalPermission;
    public RoyalGarden(Garden garden) {
        super(garden);
    }
    @Override
    public int water() {
        if (royalPermission) {
            return getNumberOfPlants() * 3 + getYearsOld() * 20;
        } else {
            return -1; // Error code when no royal permission is granted
        }
    }

    @Override
    public int plant(String plantType) {
        if (isSpaceAvailable() && royalPermission) {
            return getNumberOfPlants() + 3 + getSize() * getYearsOld();
        } else {
            return -1; // Error code when no space is available or no royal permission
        }
    }

    @Override
    public int calculateTotalLifeTime() {
        return getYearsOld() * getSize() * 3 - getNumberOfPlants() * 10;
    }
}