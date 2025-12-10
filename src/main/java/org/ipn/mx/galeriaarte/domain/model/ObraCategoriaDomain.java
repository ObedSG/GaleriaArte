package org.ipn.mx.galeriaarte.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObraCategoriaDomain {
    private Integer idObraCategoria;
    private Integer idObraDigital;
    private Integer idCategoria;

    public void validar() {
        if (idObraDigital == null) {
            throw new IllegalArgumentException("El ID de la obra digital es obligatorio");
        }
        if (idCategoria == null) {
            throw new IllegalArgumentException("El ID de la categoría es obligatorio");
        }
    }
}