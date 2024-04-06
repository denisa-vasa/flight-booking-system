package com.gisdev.crmshm.exception;

public class FlightExists extends RuntimeException{
    public FlightExists(String message) {
        super(message);
    }
}
