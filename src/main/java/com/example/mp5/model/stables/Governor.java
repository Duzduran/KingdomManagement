package com.example.mp5.model.stables;

import com.example.mp5.model.stables.RoyalStable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Governor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private int age;

    private String title;


    @ManyToOne()
    @JoinColumn(name = "royalStable_id")
    @JsonBackReference
    private RoyalStable governingStable;

}
