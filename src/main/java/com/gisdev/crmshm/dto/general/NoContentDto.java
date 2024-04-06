package com.gisdev.crmshm.dto.general;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class NoContentDto extends ResponseEntity {
    private String message;
    private HttpStatusCode status;

    public NoContentDto(String message, HttpStatusCode status) {
        super(message, status);
    }
}
