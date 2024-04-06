package com.gisdev.crmshm.dto;

import com.gisdev.crmshm.dataType.FlightStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FlightRequestDto {

    @NotNull(message = "Id should not be empty")
    private Long FlightRequestId;

    @NotNull(message = "Status should not be empty.")
    private FlightStatus flightStatus;
}
