package org.ipn.mx.galeriaarte.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "DTO para representar la relación entre obra y categoría")
public class ObraCategoriaDTO {

    @Schema(description = "ID de la relación", example = "1")
    private Integer idObraCategoria;

    @NotNull(message = "El ID de la obra digital es obligatorio")
    @Schema(description = "ID de la obra digital", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer idObraDigital;

    @NotNull(message = "El ID de la categoría es obligatorio")
    @Schema(description = "ID de la categoría", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer idCategoria;
}