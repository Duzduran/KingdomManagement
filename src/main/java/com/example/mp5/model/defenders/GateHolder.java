package com.example.mp5.model.defenders;

import com.example.mp5.model.util.enums.PrimaryWeapon;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GateHolder extends Defender {
    @NotNull
    @Min(30)
    private int stamina;

    @NotNull
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "weapon_types", joinColumns = @JoinColumn(name = "GateHolder_id"))
    private Set<PrimaryWeapon> weapons = new HashSet<>();


}
