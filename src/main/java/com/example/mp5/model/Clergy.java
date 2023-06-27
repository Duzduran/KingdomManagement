package com.example.mp5.model;

import com.example.mp5.model.util.enums.Religion;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Clergy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "mandatory!")
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Religion denomination;

    @NotNull
    private int ordinationYear;

    @ManyToOne
    @JoinColumn(name = "chapel_id")
    private Chapel chapel;



}
