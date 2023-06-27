package com.example.mp5.model.defenders;

import com.example.mp5.model.Castle;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
/**
 * Represents a defender in the castle.
 */
public abstract class Defender {
    /**
     * The unique identifier of the defender.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @NotNull
    @Min(0)
    @Max(500)
    /**
     * The health points of the defender.
     */
    private int health;
    @NotNull
    @Min(0)
    @Max(1000)
    /**
     * The damage inflicted by the defender.
     */
    private int damage;

    @ManyToOne()
    @JoinColumn(name = "castle_id")
    @JsonBackReference
    /**
     * The castle that the defender is assigned to.
     */
    private Castle defends;
}
