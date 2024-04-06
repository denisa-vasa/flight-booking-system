package com.gisdev.crmshm.service;

import com.gisdev.crmshm.dataType.TravelClass;
import com.gisdev.crmshm.dto.FlightDto;
import com.gisdev.crmshm.entity.Flight;
import com.gisdev.crmshm.entity.FlightRequest;
import com.gisdev.crmshm.entity.User;
import com.gisdev.crmshm.exception.BadRequestException;
import com.gisdev.crmshm.export.Flight.FlightExportFile;
import com.gisdev.crmshm.imports.ImportFlights;
import com.gisdev.crmshm.imports.ImportUsers;
import com.gisdev.crmshm.repository.FlightRepository;
import com.gisdev.crmshm.repository.FlightRequestRepository;
import com.gisdev.crmshm.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FlightService {

    private FlightRepository flightRepository;
    private FlightRequestRepository flightRequestRepository;
    private UserRepository userRepository;

    public Flight createFlight(Flight flight) {
        validateFlightNotEmpty(flight);
        validateFlight(flight);
        validateFlightData(flight);
        return flightRepository.save(flight);
    }

    private void validateFlightData(Flight flight) {
        List<Flight> existingFlight = flightRepository.findByDestinationAndDepartureAndDepartureTimeAndTravelClass(
                flight.getDestination(), flight.getDeparture(), flight.getDepartureTime(), flight.getTravelClass());

        if (!existingFlight.isEmpty()) {
            throw new BadRequestException("This flight already exists.");
        }
    }

    private void validateFlight(Flight flight) {
        List<String> allowedDestination = getAllowedDestinations();
        String flightDes = flight.getDestination();

        if (!allowedDestination.contains(flightDes)) {
            throw new BadRequestException("The list of strings is not equal to the target string.");
        }
    }

    private void validateFlightNotEmpty(Flight flight) {
        if (flight.getDeparture() == null || flight.getDeparture().isEmpty() ||
                flight.getDestination() == null || flight.getDestination().isEmpty() ||
                flight.getDepartureTime() == null ||
                flight.getTravelClass() == null) {
            throw new BadRequestException("Flight data should not be empty.");
        }
    }

    private List<String> getAllowedDestinations() {
        return Arrays.asList("Milano", "Tirana", "Paris");
    }

    public void setAndUpdate(Long flightId, FlightDto flightDto) {
        Flight updatedFlight = findFlightById(flightId);
        updatedFlight.setDepartureTime(flightDto.getDepartureTime());
        flightRepository.save(updatedFlight);
    }

    @Transactional
    public void deleteFlightAndFlightRequest(Long flightId) {
        Flight flight = findFlightById(flightId);
        List<FlightRequest> flightRequests = flightRequestRepository.findByFlight(flight);
        flightRequestRepository.deleteAll(flightRequests);
        flightRepository.delete(flight);
    }

    public Flight findFlightById(Long flightId) {
        return flightRepository.findById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("Flight with id " + flightId + " not found!"));
    }

    public List<Flight> getAllFlightsByDate(LocalDateTime date) {
        return flightRepository.getAllFlightsByDate(date);
    }

    public List<Flight> getFlightsByDepartureAndDestination(String departure, String destination) {
        return flightRepository.getFlightsByDepartureAndDestination(departure, destination);
    }

    public List<Flight> getFlightsByTravelClass(TravelClass travelClass) {
        return flightRepository.getFlightsByTravelClass(travelClass);
    }

    public Page<Flight> getAllFlights(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return flightRepository.findAll(pageable);
    }

    public List<FlightDto> getAllFlightsDto(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Flight> flightEntityList = flightRepository.findAll(pageable);

        //convert using stream.map
        return flightEntityList.stream().map(flight -> FlightDto.builder()
                        .departureTime(flight.getDepartureTime())
                        .destination(flight.getDestination())
                        .departure(flight.getDeparture()).build())
                .collect(Collectors.toList());
        //return requestedFlights.map(flight -> modelMapper.map(flight, FlightDto.class));
    }

    public ByteArrayInputStream exportFinalInputFile() throws IOException {
        List<Flight> flightList = flightRepository.findAll();
        ByteArrayInputStream inputStream = FlightExportFile.generateExcelFile(flightList);

        return inputStream;
    }

    public ByteArrayInputStream exportByTravelClass(TravelClass travelClass) throws IOException {
        List<Flight> flightsByTravelClass = flightRepository.getFlightsByTravelClass(travelClass);
        ByteArrayInputStream inputStream = FlightExportFile.generateExcelFile(flightsByTravelClass);
        return inputStream;
    }

    public List<Flight> importFlights(MultipartFile file) throws IOException {
        List<Flight> flights = ImportFlights.importFlights(file);

        flightRepository.saveAll(flights);
        return flights;
    }

    @org.springframework.transaction.annotation.Transactional(noRollbackFor = Exception.class)
    public void importFlightsAndUsers(MultipartFile file) throws IOException {
        try {
            List<Flight> flights = ImportFlights.importFlights(file);
            List<User> users = ImportUsers.importUsers(file);

            flightRepository.saveAll(flights);
            userRepository.saveAll(users);
        } catch (Exception e) {
            throw new RuntimeException("Failed to import flights and users!!!");
        }
    }
}
