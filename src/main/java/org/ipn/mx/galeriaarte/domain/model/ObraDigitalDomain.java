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
public class ObraDigitalDomain {
    private Integer idObraDigital;
    private String titulo;
    private String descripcion;
    private LocalDate fechaPublicacion;
    private Integer idAutor;
    private Integer idArchivoPrincipal;

    public void validar() {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título de la obra es obligatorio");
        }
        if (titulo.length() > 200) {
            throw new IllegalArgumentException("El título no puede exceder 200 caracteres");
        }
    }
}