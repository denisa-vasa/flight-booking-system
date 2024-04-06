package com.gisdev.crmshm.repository;

import com.gisdev.crmshm.entity.Flight;
import com.gisdev.crmshm.entity.FlightRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRequestRepository extends JpaRepository<FlightRequest, Long> {

    @Query("SELECT r FROM FlightRequest r WHERE r.user.id = :userId")
    List<FlightRequest> findFlightRequestByUserId(Long userId);

    List<FlightRequest> findByFlight(Flight flight);
}
