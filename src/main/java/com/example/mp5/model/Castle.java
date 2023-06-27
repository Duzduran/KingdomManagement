package com.example.mp5.model;

import com.example.mp5.model.defenders.Defender;
import com.example.mp5.model.stables.Stable;
import com.example.mp5.model.util.enums.Kingdom;
import com.example.mp5.model.util.enums.Religion;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
/**
 * Represents a Castle entity.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "castle")
public class Castle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//  @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank(message = "mandatory!")
    private String name;
    @NotNull(message = "Castle has to have some towers")
    @Min(value = 4, message = "Number of towers must be at least 4")
    @Max(value = 20, message = "Number of towers must not exceed 20")
    private int numOfTowers;

    @NotNull(message = "castle needs some inner area!")
    @DecimalMin(value = "100.0", message = "Inner area must be at least 100.0")
    private Double innerArea;
    @NotNull(message = "has to belong to a Kingdom")
    @Enumerated(EnumType.STRING)
    private Kingdom belongedKingdom;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Religion religion;
    @NotNull
    private boolean nearbyCliff;
    @NotNull
    private boolean bendOfRiver;
    @NotNull
    private int numOfInnerWalls;
    @NotNull(message = "amount of stored food is mandatory!")
    private Double amountOfStoredFood;

    /**
     * The defenders defending the castle.
     *
     * This represents the one-to-many relationship between the castle and its defenders.
     * The defenders are lazily loaded and excluded from the toString() and equals() / hashCode() methods.
     * The @JsonBackReference annotation is used to prevent infinite recursion during JSON serialization.
     */
    @OneToMany(mappedBy = "defends", fetch = FetchType.LAZY)
    @Builder.Default
    @JsonBackReference
    private Set<Defender> defenders = new HashSet<>();


    /**
     * The chapels located in the castle.
     *
     * This represents the one-to-many relationship between the castle and its chapels.
     * The chapels are eagerly loaded and excluded from the toString() and equals() / hashCode() methods.
     */
    @OneToMany( mappedBy = "belongedCastle", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Chapel> chapels = new HashSet<>();

    /**
     * The balistarias present in the castle.
     *
     * This represents the one-to-many relationship between the castle and its balistarias.
     * The balistarias are eagerly loaded and excluded from the toString() and equals() / hashCode() methods.
     */
    @OneToMany( mappedBy = "belongedPlace",cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Balistaria> balistarias = new HashSet<>();

    @OneToMany( mappedBy = "castle",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Stable> stables = new HashSet<>();
    /**
     * The gardens in the castle.
     *
     * This represents the one-to-many relationship between the castle and its gardens.
     * The gardens are lazily loaded and excluded from the toString() and equals() / hashCode() methods.
     */
    @OneToMany( mappedBy = "castle",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Stable> gardens = new HashSet<>();
}
