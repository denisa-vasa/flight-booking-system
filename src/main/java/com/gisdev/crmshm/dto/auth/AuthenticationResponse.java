package com.gisdev.crmshm.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponse implements Serializable {
    private String jwt;
}
