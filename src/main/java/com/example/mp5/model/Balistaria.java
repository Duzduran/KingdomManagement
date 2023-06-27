package com.example.mp5.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties("guardDuties")
public class Balistaria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Min(1)
    private int numberOfBolts;

    @Min(10)
    private int power;

    @OneToMany(mappedBy = "balistaria", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private Set<GuardDuty> guardDuties = new HashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "belonged_castle_id", nullable = false, updatable = false)
    private Castle belongedPlace;
}