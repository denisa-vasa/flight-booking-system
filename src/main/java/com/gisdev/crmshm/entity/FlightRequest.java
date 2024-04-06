package com.gisdev.crmshm.entity;

import com.gisdev.crmshm.dataType.FlightStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flight_request")
public class FlightRequest extends AbstractEntity {

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    @Column(name = "reason")
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_Id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
