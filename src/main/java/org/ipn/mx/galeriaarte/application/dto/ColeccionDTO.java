package org.ipn.mx.galeriaarte.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "DTO para representar una colección")
public class ColeccionDTO {

    @Schema(description = "ID de la colección", example = "1")
    private Integer idColeccion;

    @NotBlank(message = "El nombre de la colección es obligatorio")
    @Size(max = 150, message = "El nombre no puede exceder 150 caracteres")
    @Schema(description = "Nombre de la colección", example = "Arte Mexicano Contemporáneo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombreColeccion;

    @Size(max = 512, message = "La descripción no puede exceder 512 caracteres")
    @Schema(description = "Descripción de la colección", example = "Colección de obras de artistas mexicanos del siglo XXI")
    private String descripcionColeccion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Fecha de inicio de la colección", example = "2024-01-01")
    private LocalDate fechaInicio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Fecha de fin de la colección", example = "2024-12-31")
    private LocalDate fechaFin;
}