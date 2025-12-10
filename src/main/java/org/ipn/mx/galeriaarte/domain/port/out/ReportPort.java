package org.ipn.mx.galeriaarte.domain.port.out;

import org.ipn.mx.galeriaarte.domain.model.*;
import java.util.List;

public interface ReportPort {
    byte[] generarPdfAutores(List<AutorDomain> autores);
    byte[] generarPdfCategorias(List<CategoriaDomain> categorias);
    byte[] generarPdfColecciones(List<ColeccionDomain> colecciones);
    byte[] generarPdfObras(List<ObraDigitalDomain> obras);
}