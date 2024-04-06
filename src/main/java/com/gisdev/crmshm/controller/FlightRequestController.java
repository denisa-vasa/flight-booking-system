package com.gisdev.crmshm.controller;

import com.gisdev.crmshm.dto.FlightRequestDto;
import com.gisdev.crmshm.entity.FlightRequest;
import com.gisdev.crmshm.service.FlightRequestService;
import com.gisdev.crmshm.util.constant.RestConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(RestConstants.FlightRequestController.BASE_PATH)
public class FlightRequestController {

    private FlightRequestService flightRequestService;

    @PostMapping(RestConstants.FlightRequestController.SAVE_FLIGHT_REQUEST)
    public ResponseEntity<Long> createFlightRequest(@Validated @RequestBody FlightRequest flightRequest) {
        FlightRequest savedFlightRequest = flightRequestService.createFlightRequest(flightRequest);
        return new ResponseEntity<>(savedFlightRequest.getId(), HttpStatus.OK);
    }

    @PutMapping(RestConstants.FlightRequestController.UPDATE_FLIGHT_REQUEST)
    public ResponseEntity<Long> updateFlightRequest(@PathVariable Long flightRequestId,
                                                    @RequestBody FlightRequestDto flightRequestDto) {
        flightRequestService.setAndUpdate(flightRequestId, flightRequestDto);
        return new ResponseEntity<>(flightRequestId, HttpStatus.OK);
    }

    @DeleteMapping(RestConstants.FlightRequestController.DELETE_FLIGHT_REQUEST)
    public ResponseEntity<String> deleteFlightRequest(@PathVariable Long flightRequestId) {
        flightRequestService.deleteFlightRequest(flightRequestId);
        return ResponseEntity.ok("Flight deleted successfully!!!");
    }

    @GetMapping(RestConstants.FLIGHT_REQUEST_ID)
    public ResponseEntity<FlightRequest> getFlightRequestById(@PathVariable Long flightRequestId) {
        FlightRequest flightRequest = flightRequestService.findFlightRequestById(flightRequestId);
        return new ResponseEntity<>(flightRequest, HttpStatus.OK);
    }

    @GetMapping(RestConstants.FlightController.FLIGHT_USER_ID)
    public ResponseEntity<List<FlightRequest>> getFlightRequestByUserId(@PathVariable Long userId) {
        List<FlightRequest> flightRequests = flightRequestService.findFlightRequestByUserId(userId);
        return new ResponseEntity<>(flightRequests, HttpStatus.OK);
    }
}
