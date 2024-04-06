package com.gisdev.crmshm.controller;

import com.gisdev.crmshm.dataType.TravelClass;
import com.gisdev.crmshm.dto.FlightDto;
import com.gisdev.crmshm.entity.Flight;
import com.gisdev.crmshm.service.FlightService;
import com.gisdev.crmshm.util.constant.RestConstants;
import com.gisdev.crmshm.util.general.FileUtil;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(RestConstants.FlightController.BASE_PATH)
public class FlightController {

    private FlightService flightService;

    @PostMapping(RestConstants.FlightController.SAVE_FLIGHT)
    public ResponseEntity<Long> createFlight(@Validated @RequestBody Flight flight) {
        Flight savedFlight = flightService.createFlight(flight);
        return new ResponseEntity<>(savedFlight.getId(), HttpStatus.OK);
    }

    @PutMapping(RestConstants.FlightController.UPDATE_FLIGHT)
    public ResponseEntity<Long> updateFlight(@PathVariable Long flightId,
                                             @Validated @RequestBody FlightDto flightDto) {
        flightService.setAndUpdate(flightId, flightDto);
        return new ResponseEntity<>(flightId, HttpStatus.OK);
    }

    @DeleteMapping(RestConstants.FlightController.DELETE_FLIGHT)
    public ResponseEntity<?> deleteFlight(@PathVariable Long flightId) {
        flightService.deleteFlightAndFlightRequest(flightId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(RestConstants.FLIGHT_ID)
    public ResponseEntity<Flight> findFlightById(@PathVariable Long flightId) {
        Flight flight = flightService.findFlightById(flightId);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @GetMapping(RestConstants.FlightController.FLIGHT_BY_DATE)
    public ResponseEntity<List<Flight>> getAllFlightsByDateAsc(@PathVariable LocalDateTime date) {
        List<Flight> flights = flightService.getAllFlightsByDate(date);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping(RestConstants.FlightController.FLIGHT_BY_DEPARTURE_DESTINATION)
    public ResponseEntity<List<Flight>> getFlightsByDepartureAndDestination(@RequestParam String departure,
                                                                            @RequestParam String destination) {
        List<Flight> departureAndDestination = flightService.getFlightsByDepartureAndDestination(departure, destination);
        return new ResponseEntity<>(departureAndDestination, HttpStatus.OK);
    }

    @GetMapping(RestConstants.FlightController.FLIGHT_BY_TRAVEL_CLASS)
    public ResponseEntity<List<Flight>> getFlightsByTravelClass(@RequestParam TravelClass travelClass) {
        List<Flight> flightsByTravelClass = flightService.getFlightsByTravelClass(travelClass);
        return new ResponseEntity<>(flightsByTravelClass, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Flight>> getAllFlights(@RequestParam int pageNumber,
                                                      @RequestParam int pageSize) {
        Page<Flight> allFlights = flightService.getAllFlights(pageNumber, pageSize);
        return new ResponseEntity<>(allFlights, HttpStatus.OK);
    }

    @GetMapping(RestConstants.FlightController.ALL_FLIGHTS)
    public ResponseEntity<List<FlightDto>> getAllFlightsByPage(@RequestParam int pageNumber,
                                                               @RequestParam int pageSize) {
        List<FlightDto> allFlights = flightService.getAllFlightsDto(pageNumber, pageSize);
        return new ResponseEntity<>(allFlights, HttpStatus.OK);
    }

    @GetMapping(RestConstants.FlightController.EXPORT_FINAL_INPUT_FILE)
    public ResponseEntity<InputStreamResource> exportFinalInputFile() throws IOException {

        String filename = "FlightsData.xlsx";
        ByteArrayInputStream file = flightService.exportFinalInputFile();
        return ResponseEntity.ok().headers(FileUtil.buildHttpHeaderForFile(filename)).body(new InputStreamResource(file));
    }

    @GetMapping(RestConstants.FlightController.EXPORT_FLIGHTS_BY_TRAVEL_CLASS)
    public ResponseEntity<InputStreamResource> exportFlightsByTravelClass(@RequestParam TravelClass travelClass) throws IOException {

        String filename = "FlightByTravelClass.xlsx";
        ByteArrayInputStream file = flightService.exportByTravelClass(travelClass);
        return ResponseEntity.ok().headers(FileUtil.buildHttpHeaderForFile(filename)).body(new InputStreamResource(file));
    }

    @PostMapping(RestConstants.FlightController.UPLOAD)
    public ResponseEntity<String> importFlights(@RequestParam("file") MultipartFile file) {
        try {
            List<Flight> flights = flightService.importFlights(file);
            return ResponseEntity.ok("File imported successfully!!!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping(RestConstants.FlightController.UPLOAD_FLIGHTS_AND_USER)
    public ResponseEntity<String> importFlightsAndUsers(@RequestParam("file") MultipartFile file) {
        try {
            flightService.importFlightsAndUsers(file);
            return ResponseEntity.ok("File imported successfully!!!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}

