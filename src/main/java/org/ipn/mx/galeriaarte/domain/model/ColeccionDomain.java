package org.ipn.mx.galeriaarte.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColeccionDomain {
    private Integer idColeccion;
    private String nombreColeccion;
    private String descripcionColeccion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public void validar() {
        if (nombreColeccion == null || nombreColeccion.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la colección es obligatorio");
        }
        if (fechaInicio != null && fechaFin != null && fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
    }
}