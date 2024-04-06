package com.gisdev.crmshm.repository;

import com.gisdev.crmshm.dataType.TravelClass;
import com.gisdev.crmshm.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f WHERE DATE(f.departureTime) = DATE(:date) ORDER BY f.departureTime ASC")
    List<Flight> getAllFlightsByDate(@Param("date") LocalDateTime date);

    List<Flight> getFlightsByDepartureAndDestination(String departure, String destination);

    List<Flight> getFlightsByTravelClass(TravelClass travelClass);

    List<Flight> findByDestinationAndDepartureAndDepartureTimeAndTravelClass(String destination, String departure,
                                                                             LocalDateTime departureTime,
                                                                             TravelClass travelClass);

}
