package com.gisdev.crmshm.service;

import com.gisdev.crmshm.dataType.Destination;
import com.gisdev.crmshm.dto.FlightRequestDto;
import com.gisdev.crmshm.entity.FlightRequest;
import com.gisdev.crmshm.entity.User;
import com.gisdev.crmshm.exception.*;
import com.gisdev.crmshm.repository.FlightRepository;
import com.gisdev.crmshm.repository.FlightRequestRepository;
import com.gisdev.crmshm.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FlightRequestService {

    private FlightRequestRepository flightRequestRepository;
    private FlightRepository flightRepository;
    private UserRepository userRepository;

    public FlightRequest createFlightRequest(FlightRequest flightRequest) {
        // Validate destination before saving the flight request
        if (!isValidDestination(flightRequest.getFlight().getDestination())) {
            throw new IllegalArgumentException("Invalid destination");
        }
        return flightRepository.findById(flightRequest.getFlight().getId()).map(flight -> {
            User user = userRepository.findById(flightRequest.getUser().getId()).orElseThrow(
                    () -> new UserNotFoundException("User not found"));

            flightRequest.setFlight(flight);
            flightRequest.setUser(user);

            return flightRequestRepository.save(flightRequest);
        }).orElseThrow(() -> new FlightNotFoundException("Flight not found."));
    }

    public boolean isValidDestination(String destination) {
        for (Destination allowedDestination : Destination.values()) {
            if (allowedDestination.getName().equalsIgnoreCase(destination)) {
                return true; // Destination is valid
            }
        }
        return false; // Destination is not valid
    }

    public void setAndUpdate(Long flightRequestId, FlightRequestDto flightRequestDto) {
        FlightRequest flightRequest = findFlightRequestById(flightRequestId);
        flightRequest.setStatus(flightRequestDto.getFlightStatus());
        flightRequestRepository.save(flightRequest);
    }

    public void deleteFlightRequest(Long flightRequestId) {
        flightRequestRepository.deleteById(flightRequestId);
    }

    public FlightRequest findFlightRequestById(Long flightRequestId) {
        return flightRequestRepository.findById(flightRequestId)
                .orElseThrow(() -> new FlightRequestNotFoundException(
                        "Flight Request with id " + flightRequestId + " not found"));
    }

    public List<FlightRequest> findFlightRequestByUserId(Long userId) {
        return flightRequestRepository.findFlightRequestByUserId(userId);
    }
}
