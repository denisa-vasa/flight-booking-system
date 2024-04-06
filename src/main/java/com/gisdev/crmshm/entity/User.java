package com.gisdev.crmshm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends AbstractEntity {

    @Column(name = "username")
    @NotBlank(message = "Username should not be empty.")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "Password should not be empty.")
    private String password;

    @Column(name = "role")
    @NotBlank(message = "Role should not be empty.")
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FlightRequest> flightRequestEntityList;

}
