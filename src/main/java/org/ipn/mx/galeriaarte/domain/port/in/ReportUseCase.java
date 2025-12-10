package org.ipn.mx.galeriaarte.domain.port.in;

public interface ReportUseCase {
    byte[] generarReporteAutores();
    byte[] generarReporteCategorias();
    byte[] generarReporteColecciones();
    byte[] generarReporteObras();
    byte[] generarReporteObrasPorAutor(Integer idAutor);
    byte[] generarReporteObrasPorCategoria(Integer idCategoria);
    byte[] generarReporteObrasPorColeccion(Integer idColeccion);
}