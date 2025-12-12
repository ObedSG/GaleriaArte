package org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.port.in.ReportUseCase;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
@Tag(name = "Reportes", description = "API para generación de reportes en PDF")
public class ReportController {

    private final ReportUseCase reportUseCase;

    @Operation(summary = "Generar reporte de autores",
            description = "Genera un reporte en PDF con todos los autores")
    @GetMapping("/autores")
    public ResponseEntity<byte[]> generarReporteAutores() {
        log.info("GET /api/reportes/autores - Generando reporte de autores");

        byte[] pdfBytes = reportUseCase.generarReporteAutores();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_autores.pdf")
                .body(pdfBytes);
    }

    @Operation(summary = "Generar reporte de categorías",
            description = "Genera un reporte en PDF con todas las categorías")
    @GetMapping("/categorias")
    public ResponseEntity<byte[]> generarReporteCategorias() {
        log.info("GET /api/reportes/categorias - Generando reporte de categorías");

        byte[] pdfBytes = reportUseCase.generarReporteCategorias();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_categorias.pdf")
                .body(pdfBytes);
    }

    @Operation(summary = "Generar reporte de colecciones",
            description = "Genera un reporte en PDF con todas las colecciones")
    @GetMapping("/colecciones")
    public ResponseEntity<byte[]> generarReporteColecciones() {
        log.info("GET /api/reportes/colecciones - Generando reporte de colecciones");

        byte[] pdfBytes = reportUseCase.generarReporteColecciones();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_colecciones.pdf")
                .body(pdfBytes);
    }

    @Operation(summary = "Generar reporte de obras",
            description = "Genera un reporte en PDF con todas las obras digitales")
    @GetMapping("/obras")
    public ResponseEntity<byte[]> generarReporteObras() {
        log.info("GET /api/reportes/obras - Generando reporte de obras");

        byte[] pdfBytes = reportUseCase.generarReporteObras();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_obras.pdf")
                .body(pdfBytes);
    }

    @Operation(summary = "Generar reporte de obras por autor",
            description = "Genera un reporte en PDF con las obras de un autor específico")
    @GetMapping("/obras/autor/{idAutor}")
    public ResponseEntity<byte[]> generarReporteObrasPorAutor(
            @Parameter(description = "ID del autor") @PathVariable Integer idAutor) {
        log.info("GET /api/reportes/obras/autor/{} - Generando reporte", idAutor);

        byte[] pdfBytes = reportUseCase.generarReporteObrasPorAutor(idAutor);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=reporte_obras_autor_" + idAutor + ".pdf")
                .body(pdfBytes);
    }

    @Operation(summary = "Generar reporte de obras por categoría",
            description = "Genera un reporte en PDF con las obras de una categoría específica")
    @GetMapping("/obras/categoria/{idCategoria}")
    public ResponseEntity<byte[]> generarReporteObrasPorCategoria(
            @Parameter(description = "ID de la categoría") @PathVariable Integer idCategoria) {
        log.info("GET /api/reportes/obras/categoria/{} - Generando reporte", idCategoria);

        byte[] pdfBytes = reportUseCase.generarReporteObrasPorCategoria(idCategoria);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=reporte_obras_categoria_" + idCategoria + ".pdf")
                .body(pdfBytes);
    }

    @Operation(summary = "Generar reporte de obras por colección",
            description = "Genera un reporte en PDF con las obras de una colección específica")
    @GetMapping("/obras/coleccion/{idColeccion}")
    public ResponseEntity<byte[]> generarReporteObrasPorColeccion(
            @Parameter(description = "ID de la colección") @PathVariable Integer idColeccion) {
        log.info("GET /api/reportes/obras/coleccion/{} - Generando reporte", idColeccion);

        byte[] pdfBytes = reportUseCase.generarReporteObrasPorColeccion(idColeccion);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=reporte_obras_coleccion_" + idColeccion + ".pdf")
                .body(pdfBytes);
    }
}
