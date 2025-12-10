package org.ipn.mx.galeriaarte.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutorDomain {
    private Integer idAutor;
    private String nombreCompleto;
    private String correoContacto;
    private String avatar;

    // Validaciones de negocio
    public void validar() {
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del autor es obligatorio");
        }
        if (nombreCompleto.length() > 100) {
            throw new IllegalArgumentException("El nombre no puede exceder 100 caracteres");
        }
    }
}