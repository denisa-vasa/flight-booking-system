package com.gisdev.crmshm.dto.auth;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthenticationRequest implements Serializable {
    private String emerPerdoruesi;
    private String fjalekalimi;
}