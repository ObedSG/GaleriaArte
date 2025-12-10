package org.ipn.mx.galeriaarte.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColeccionObraDomain {
    private Integer idColeccionObra;
    private Integer idObraDigital;
    private Integer idColeccion;

    public void validar() {
        if (idObraDigital == null) {
            throw new IllegalArgumentException("El ID de la obra digital es obligatorio");
        }
        if (idColeccion == null) {
            throw new IllegalArgumentException("El ID de la colección es obligatorio");
        }
    }
}