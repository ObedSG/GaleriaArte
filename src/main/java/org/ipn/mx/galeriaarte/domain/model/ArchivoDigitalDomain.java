package org.ipn.mx.galeriaarte.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArchivoDigitalDomain {
    private Integer idArchivo;
    private String ruta;
    private String formato;
    private String checksum;
    private Integer idObraDigital;

    public void validar() {
        if (ruta == null || ruta.trim().isEmpty()) {
            throw new IllegalArgumentException("La ruta del archivo es obligatoria");
        }
        if (formato == null || formato.trim().isEmpty()) {
            throw new IllegalArgumentException("El formato del archivo es obligatorio");
        }
    }
}