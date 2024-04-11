package com.gisdev.crmshm.entity;

import com.gisdev.crmshm.dataType.TravelClass;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flight")
public class Flight extends AbstractEntity {

    @Column(name = "departure")
    @NotBlank(message = "Departure should not be empty.")
    private String departure;

    @Column(name = "destination")
    @NotBlank(message = "Destination should not be empty.")
    private String destination;

    @Column(name = "departureTime")
    private LocalDateTime departureTime;

    @Column(name = "class")
    @Enumerated(EnumType.STRING)
    private TravelClass travelClass;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<FlightRequest> flightRequestEntityList;

}
