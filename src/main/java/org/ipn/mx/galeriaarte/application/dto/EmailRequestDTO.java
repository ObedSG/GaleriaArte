package org.ipn.mx.galeriaarte.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para solicitud de envío de email")
public class EmailRequestDTO {

    @NotBlank(message = "El destinatario es obligatorio")
    @Email(message = "Debe ser un correo válido")
    @Schema(description = "Correo del destinatario", example = "usuario@ejemplo.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String destinatario;

    @NotBlank(message = "El asunto es obligatorio")
    @Schema(description = "Asunto del correo", example = "Nueva obra publicada", requiredMode = Schema.RequiredMode.REQUIRED)
    private String asunto;

    @NotBlank(message = "El contenido es obligatorio")
    @Schema(description = "Contenido del correo", example = "Se ha publicado una nueva obra en la galería", requiredMode = Schema.RequiredMode.REQUIRED)
    private String contenido;
}