package org.ipn.mx.galeriaarte.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Schema(description = "DTO para representar un archivo digital")
public class ArchivoDigitalDTO {

    @Schema(description = "ID del archivo", example = "1")
    private Integer idArchivo;

    @NotBlank(message = "La ruta es obligatoria")
    @Size(max = 512, message = "La ruta no puede exceder 512 caracteres")
    @Schema(description = "Ruta del archivo", example = "/uploads/obras/imagen001.png", requiredMode = Schema.RequiredMode.REQUIRED)
    private String ruta;

    @NotBlank(message = "El formato es obligatorio")
    @Size(max = 20, message = "El formato no puede exceder 20 caracteres")
    @Schema(description = "Formato del archivo", example = "PNG", requiredMode = Schema.RequiredMode.REQUIRED)
    private String formato;

    @NotBlank(message = "El checksum es obligatorio")
    @Size(max = 128, message = "El checksum no puede exceder 128 caracteres")
    @Schema(description = "Checksum del archivo para verificación", example = "a3d5e6f7g8h9i0j1k2l3m4n5", requiredMode = Schema.RequiredMode.REQUIRED)
    private String checksum;

    @NotNull(message = "El ID de la obra digital es obligatorio")
    @Schema(description = "ID de la obra digital asociada", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer idObraDigital;
}