package com.example.mp5.model.stables;

import com.example.mp5.model.util.interfaces.IRoyalStable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "royalStable")
public class RoyalStable extends Stable implements IRoyalStable {

    @OneToMany(mappedBy = "governingStable", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Governor> governors = new HashSet<>();

    @NotNull(message = "Number of tools is mandatory!")
    private int numOfTools;

    public int calculateParadeDuration() {
        int numOfHorses = getNumOfHorses();
        int durationPerHorse = 15; // Duration in minutes per horse

        return numOfHorses * durationPerHorse;
    }

    public String createParadeMessage() {
        String joinedNames = governors.stream()
                .map(Governor::getName)
                .collect(Collectors.joining(", "));

        StringBuilder message = new StringBuilder();
        message.append("We proudly present the royal parade with our Governors: ")
                .append(joinedNames)
                .append(" leading our way!");

        int numOfHorses = getNumOfHorses();
        if (numOfHorses > 10) {
            message.append("It's a grand spectacle with ");
            message.append(numOfHorses);
            message.append(" magnificent horses showcasing their beauty and elegance. ");
        } else {
            message.append("A smaller but equally enchanting parade awaits you with ");
            message.append(numOfHorses);
            message.append(" graceful horses. ");
        }

        int numOfParticipants = calculateNumOfParticipants();
        message.append("We have ");
        message.append(numOfParticipants);
        message.append(" participants accompanying the horses, making it a truly royal affair.");

        return message.toString();
    }

    public int calculateNumOfParticipants() {
        int numOfHorses = getNumOfHorses();
        int numOfParticipants = numOfHorses / 2; // 2 people per horse

        return numOfParticipants;
    }

    @Override
    public void conductRoyalParade() {
        int paradeDuration = calculateParadeDuration();
        String paradeMessage = createParadeMessage();

        System.out.println("Conducting a royal parade from the royal stable: " + getName());
        System.out.println("Parade duration: " + paradeDuration + " minutes");
        System.out.println("Parade Message: " + paradeMessage);

    }
}