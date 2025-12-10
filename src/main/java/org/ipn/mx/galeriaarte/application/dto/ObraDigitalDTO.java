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
@Schema(description = "DTO para representar una obra digital")
public class ObraDigitalDTO {

    @Schema(description = "ID de la obra digital", example = "1")
    private Integer idObraDigital;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 200, message = "El título no puede exceder 200 caracteres")
    @Schema(description = "Título de la obra", example = "La Casa Azul", requiredMode = Schema.RequiredMode.REQUIRED)
    private String titulo;

    @Size(max = 512, message = "La descripción no puede exceder 512 caracteres")
    @Schema(description = "Descripción de la obra", example = "Pintura digital inspirada en la casa de Frida Kahlo")
    private String descripcion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Fecha de publicación", example = "2024-06-15")
    private LocalDate fechaPublicacion;

    @Schema(description = "ID del autor", example = "1")
    private Integer idAutor;

    @Schema(description = "ID del archivo principal", example = "1")
    private Integer idArchivoPrincipal;
}