package org.ipn.mx.galeriaarte.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
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
@Schema(description = "DTO para representar un autor")
public class AutorDTO {

    @Schema(description = "ID del autor", example = "1")
    private Integer idAutor;

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Schema(description = "Nombre completo del autor", example = "Diego Rivera", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombreCompleto;

    @Email(message = "Debe ser un correo válido")
    @Size(max = 100, message = "El correo no puede exceder 100 caracteres")
    @Schema(description = "Correo de contacto del autor", example = "diego.rivera@arte.com")
    private String correoContacto;

    @Size(max = 255, message = "La URL del avatar no puede exceder 255 caracteres")
    @Schema(description = "URL del avatar del autor", example = "https://ejemplo.com/avatar.jpg")
    private String avatar;
}