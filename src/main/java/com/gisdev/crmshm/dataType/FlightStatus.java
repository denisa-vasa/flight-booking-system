package com.gisdev.crmshm.dataType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FlightStatus {
    PENDING("Pending"),
    ACCEPTED("Accepted"),
    REQUESTED("Requested"),
    REJECTED("Rejected");

    public String name;
}
