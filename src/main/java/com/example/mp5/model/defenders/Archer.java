package com.example.mp5.model.defenders;


import com.example.mp5.model.GuardDuty;
import com.example.mp5.model.util.enums.ArrowType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
@JsonIgnoreProperties("guardDuties")
/**
 * Represents an archer, a type of defender in the castle.
 */
public class Archer extends Defender {
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "arrow_types", joinColumns = @JoinColumn(name = "archer_id"))
    private Set<ArrowType> arrowTypes = new HashSet<>();
    /**
     * The number of arrows possessed by the archer.
     */
    @NotNull(message = "num of arrow mandatory")
    @Min(1)
    private int numOfArrows;

    /**
     * The guard duties assigned to the archer.
     */
    @OneToMany(mappedBy = "archer", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<GuardDuty> guardDuties = new HashSet<>();
}
