package com.gisdev.crmshm.util.apiDocs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class ProfiliControllerDocs {

    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(value = RetentionPolicy.RUNTIME)
    @Operation(summary = "Merr te gjitha profilet", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "Nuk aksesohet", content = @Content),
            @ApiResponse(responseCode = "404", description = "Nuk gjendet", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)
    }, description = "Filtri qe suportohet eshte per fushen emri. Formati filter=emri:ilike:vlere")

    public @interface GetAllProfilisDoc {
    }
}
