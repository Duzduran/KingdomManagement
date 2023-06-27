package com.example.mp5.model.gardens;

import com.example.mp5.model.Castle;
import com.example.mp5.model.util.enums.GardenType;
import com.example.mp5.model.util.interfaces.GardenRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Garden  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Garden(Garden garden) {
        this.location = garden.getLocation();
        this.size = garden.getSize();
        this.numberOfPlants = garden.getNumberOfPlants();
        this.yearsOld = garden.getYearsOld();
        this.castle = garden.getCastle();
    }

    private String location;
    public int size;
    public int numberOfPlants;
    public int yearsOld; // Added for the sake of example
    @Enumerated(EnumType.STRING)
    private GardenType gardenRoleEnum;

    @Transient
    private GardenRole role;

    public abstract int water();

    public abstract int plant(String plantType);

    protected boolean isSpaceAvailable() {
        return size > numberOfPlants;
    }

    // Hypothetical method that gives you an idea of how to implement
    public abstract int calculateTotalLifeTime();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "castle_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Castle castle;
}