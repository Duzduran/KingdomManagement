package com.example.mp5.model.stables;

import com.example.mp5.model.Castle;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Stable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "mandatory!")
    private String name;
    @NotNull(message = "Stable has to have some horses")
    @Max(value = 50, message = "Number of horses must not exceed 50")
    private int numOfHorses;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "castle_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Castle castle;
}