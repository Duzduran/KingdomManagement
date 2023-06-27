package com.example.mp5.model.gardens;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class PublicGarden extends Garden {

    private boolean isRainyWeather;
    public PublicGarden(Garden garden) {
        super(garden);
    }
    @Override
    public int water() {
        if (!isRainyWeather) {
            return getNumberOfPlants() * getSize() + getYearsOld() * 10;
        } else {
            return -1; // Error code when it's rainy weather
        }
    }

    @Override
    public int plant(String plantType) {
        if (isSpaceAvailable()) {
            return (getNumberOfPlants() + 1) * getSize() - getYearsOld() * 5;
        } else {
            return -1; // Error code when no space is available
        }
    }

    @Override
    public int calculateTotalLifeTime() {
        return (getYearsOld() * getSize()) / (getNumberOfPlants() + 1);
    }
}

