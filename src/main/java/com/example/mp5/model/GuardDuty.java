package com.example.mp5.model;


import com.example.mp5.model.defenders.Archer;
import com.example.mp5.model.util.enums.DutyStatus;
import com.example.mp5.model.util.enums.Village;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"archer_id","balistaria_id"})
})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class GuardDuty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    @ManyToOne
    @JoinColumn(name = "archer_id", nullable = false)
    private Archer archer;

    @ManyToOne
    @JoinColumn(name = "balistaria_id", nullable = false)
    @JsonBackReference
    private Balistaria balistaria;

    @Enumerated(EnumType.STRING)
    private
    DutyStatus status;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "village_types", joinColumns = @JoinColumn(name = "guardduty_id"))
    private Set<Village> visibleHorizon;

}
