package org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Respuesta de error de la API")
public class ErrorResponse {

    @Schema(description = "Código de estado HTTP", example = "400")
    private int status;

    @Schema(description = "Mensaje de error", example = "Error de validación")
    private String message;

    @Schema(description = "Lista de errores detallados")
    private List<String> errors;

    @Schema(description = "Timestamp del error")
    private LocalDateTime timestamp;

    @Schema(description = "Ruta de la petición", example = "/api/autores")
    private String path;
}