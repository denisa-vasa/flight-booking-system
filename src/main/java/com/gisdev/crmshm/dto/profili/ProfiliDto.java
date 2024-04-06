package com.gisdev.crmshm.dto.profili;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfiliDto {

    @NotNull(message = "pershkrimi nuk duhet te jete bosh")
    private String pershkrimi;
}
