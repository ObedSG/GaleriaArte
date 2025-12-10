package org.ipn.mx.galeriaarte.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para solicitud de generación de reporte")
public class ReportRequestDTO {

    @Schema(description = "Tipo de reporte", example = "AUTORES", allowableValues = {"AUTORES", "CATEGORIAS", "COLECCIONES", "OBRAS"})
    private String tipoReporte;

    @Schema(description = "ID del filtro (autor, categoría, colección)", example = "1")
    private Integer idFiltro;
}