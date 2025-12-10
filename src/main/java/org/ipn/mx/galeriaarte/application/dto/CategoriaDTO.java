package org.ipn.mx.galeriaarte.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "DTO para representar una categoría")
public class CategoriaDTO {

    @Schema(description = "ID de la categoría", example = "1")
    private Integer idCategoria;

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
    @Schema(description = "Nombre de la categoría", example = "Pintura Abstracta", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombreCategoria;

    @Size(max = 250, message = "La descripción no puede exceder 250 caracteres")
    @Schema(description = "Descripción de la categoría", example = "Obras de arte abstracto contemporáneo")
    private String descripcionCategoria;
}