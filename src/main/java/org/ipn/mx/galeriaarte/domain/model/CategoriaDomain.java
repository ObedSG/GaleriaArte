package org.ipn.mx.galeriaarte.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDomain {
    private Integer idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;

    public void validar() {
        if (nombreCategoria == null || nombreCategoria.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría es obligatorio");
        }
        if (nombreCategoria.length() > 50) {
            throw new IllegalArgumentException("El nombre no puede exceder 50 caracteres");
        }
    }
}