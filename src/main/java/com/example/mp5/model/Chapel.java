package com.example.mp5.model;

import com.example.mp5.model.util.enums.Areas;
import com.example.mp5.model.util.enums.Religion;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Chapel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotNull(message = "has to belong to a Area")
    @Enumerated(EnumType.STRING)
    private Areas locationInCastle;

    @Min(value = 50, message = "Number of capacity must be at least 4")
    @Max(value = 500, message = "Number of cap cant be more than 500")
    private int capacity;
    @NotNull
    private boolean isHoldDay;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Religion religion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "belonged_castle_id", nullable = false, updatable = false)
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Castle belongedCastle;

    @OneToMany(mappedBy = "chapel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Clergy> clergies = new ArrayList<>();

}
