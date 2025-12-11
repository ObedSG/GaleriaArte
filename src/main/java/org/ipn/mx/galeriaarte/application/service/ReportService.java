package org.ipn.mx.galeriaarte.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.exception.BusinessException;
import org.ipn.mx.galeriaarte.domain.exception.EntityNotFoundException;
import org.ipn.mx.galeriaarte.domain.model.*;
import org.ipn.mx.galeriaarte.domain.port.in.ReportUseCase;
import org.ipn.mx.galeriaarte.domain.port.out.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportService implements ReportUseCase {

    private final ReportPort reportPort;
    private final AutorRepositoryPort autorRepositoryPort;
    private final CategoriaRepositoryPort categoriaRepositoryPort;
    private final ColeccionRepositoryPort coleccionRepositoryPort;
    private final ObraDigitalRepositoryPort obraDigitalRepositoryPort;

    @Override
    public byte[] generarReporteAutores() {
        log.info("Generando reporte de todos los autores");

        try {
            List<AutorDomain> autores = autorRepositoryPort.buscarTodos();

            if (autores.isEmpty()) {
                throw new BusinessException("No hay autores para generar el reporte");
            }

            byte[] pdfBytes = reportPort.generarPdfAutores(autores);
            log.info("Reporte de autores generado exitosamente. Total: {}", autores.size());
            return pdfBytes;

        } catch (Exception e) {
            log.error("Error al generar reporte de autores", e);
            throw new BusinessException("Error al generar reporte de autores: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] generarReporteCategorias() {
        log.info("Generando reporte de todas las categorías");

        try {
            List<CategoriaDomain> categorias = categoriaRepositoryPort.buscarTodas();

            if (categorias.isEmpty()) {
                throw new BusinessException("No hay categorías para generar el reporte");
            }

            byte[] pdfBytes = reportPort.generarPdfCategorias(categorias);
            log.info("Reporte de categorías generado exitosamente. Total: {}", categorias.size());
            return pdfBytes;

        } catch (Exception e) {
            log.error("Error al generar reporte de categorías", e);
            throw new BusinessException("Error al generar reporte de categorías: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] generarReporteColecciones() {
        log.info("Generando reporte de todas las colecciones");

        try {
            List<ColeccionDomain> colecciones = coleccionRepositoryPort.buscarTodas();

            if (colecciones.isEmpty()) {
                throw new BusinessException("No hay colecciones para generar el reporte");
            }

            byte[] pdfBytes = reportPort.generarPdfColecciones(colecciones);
            log.info("Reporte de colecciones generado exitosamente. Total: {}", colecciones.size());
            return pdfBytes;

        } catch (Exception e) {
            log.error("Error al generar reporte de colecciones", e);
            throw new BusinessException("Error al generar reporte de colecciones: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] generarReporteObras() {
        log.info("Generando reporte de todas las obras");

        try {
            List<ObraDigitalDomain> obras = obraDigitalRepositoryPort.buscarTodas();

            if (obras.isEmpty()) {
                throw new BusinessException("No hay obras para generar el reporte");
            }

            byte[] pdfBytes = reportPort.generarPdfObras(obras);
            log.info("Reporte de obras generado exitosamente. Total: {}", obras.size());
            return pdfBytes;

        } catch (Exception e) {
            log.error("Error al generar reporte de obras", e);
            throw new BusinessException("Error al generar reporte de obras: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] generarReporteObrasPorAutor(Integer idAutor) {
        log.info("Generando reporte de obras del autor con ID: {}", idAutor);

        if (idAutor == null) {
            throw new BusinessException("El ID del autor es obligatorio");
        }

        if (!autorRepositoryPort.existePorId(idAutor)) {
            throw new EntityNotFoundException("Autor", idAutor);
        }

        try {
            List<ObraDigitalDomain> obras = obraDigitalRepositoryPort.buscarPorAutor(idAutor);

            if (obras.isEmpty()) {
                throw new BusinessException("El autor no tiene obras para generar el reporte");
            }

            byte[] pdfBytes = reportPort.generarPdfObras(obras);
            log.info("Reporte de obras por autor generado exitosamente. Total: {}", obras.size());
            return pdfBytes;

        } catch (EntityNotFoundException | BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al generar reporte de obras por autor", e);
            throw new BusinessException("Error al generar reporte de obras por autor: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] generarReporteObrasPorCategoria(Integer idCategoria) {
        log.info("Generando reporte de obras de la categoría con ID: {}", idCategoria);

        if (idCategoria == null) {
            throw new BusinessException("El ID de la categoría es obligatorio");
        }

        if (!categoriaRepositoryPort.existePorId(idCategoria)) {
            throw new EntityNotFoundException("Categoría", idCategoria);
        }

        try {
            // TODO: Implementar método en repository para buscar obras por categoría
            List<ObraDigitalDomain> obras = List.of(); // Placeholder

            if (obras.isEmpty()) {
                throw new BusinessException("La categoría no tiene obras para generar el reporte");
            }

            byte[] pdfBytes = reportPort.generarPdfObras(obras);
            log.info("Reporte de obras por categoría generado exitosamente. Total: {}", obras.size());
            return pdfBytes;

        } catch (EntityNotFoundException | BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al generar reporte de obras por categoría", e);
            throw new BusinessException("Error al generar reporte de obras por categoría: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] generarReporteObrasPorColeccion(Integer idColeccion) {
        log.info("Generando reporte de obras de la colección con ID: {}", idColeccion);

        if (idColeccion == null) {
            throw new BusinessException("El ID de la colección es obligatorio");
        }

        if (!coleccionRepositoryPort.existePorId(idColeccion)) {
            throw new EntityNotFoundException("Colección", idColeccion);
        }

        try {
            // TODO: Implementar método en repository para buscar obras por colección
            List<ObraDigitalDomain> obras = List.of(); // Placeholder

            if (obras.isEmpty()) {
                throw new BusinessException("La colección no tiene obras para generar el reporte");
            }

            byte[] pdfBytes = reportPort.generarPdfObras(obras);
            log.info("Reporte de obras por colección generado exitosamente. Total: {}", obras.size());
            return pdfBytes;

        } catch (EntityNotFoundException | BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al generar reporte de obras por colección", e);
            throw new BusinessException("Error al generar reporte de obras por colección: " + e.getMessage(), e);
        }
    }
}