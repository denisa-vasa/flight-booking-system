package com.gisdev.crmshm.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class FlightDto {

    @NotNull(message = "The date and time should not be empty.")
    private LocalDateTime departureTime;

    @NotNull(message = "Departure should nor be empty.")
    private String departure;

    @NotNull(message = "Destination should not be empty.")
    private String destination;
}
