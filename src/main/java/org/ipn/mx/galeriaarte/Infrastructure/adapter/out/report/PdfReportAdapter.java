package org.ipn.mx.galeriaarte.Infrastructure.adapter.out.report;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.model.*;
import org.ipn.mx.galeriaarte.domain.port.out.ReportPort;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Component
public class PdfReportAdapter implements ReportPort {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public byte[] generarPdfAutores(List<AutorDomain> autores) {
        log.info("Generando PDF de autores. Total: {}", autores.size());

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Título
            agregarTitulo(document, "Reporte de Autores");
            agregarFechaGeneracion(document);

            // Tabla
            float[] columnWidths = {1, 3, 3, 2};
            Table table = new Table(UnitValue.createPercentArray(columnWidths));
            table.setWidth(UnitValue.createPercentValue(100));

            // Encabezados
            agregarEncabezadoTabla(table, new String[]{"ID", "Nombre Completo", "Correo", "Avatar"});

            // Datos
            for (AutorDomain autor : autores) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(autor.getIdAutor()))));
                table.addCell(new Cell().add(new Paragraph(autor.getNombreCompleto())));
                table.addCell(new Cell().add(new Paragraph(
                        autor.getCorreoContacto() != null ? autor.getCorreoContacto() : "N/A")));
                table.addCell(new Cell().add(new Paragraph(
                        autor.getAvatar() != null ? "Sí" : "No")));
            }

            document.add(table);

            // Total
            agregarTotal(document, "Total de autores: " + autores.size());

            document.close();
            log.info("PDF de autores generado exitosamente");
            return baos.toByteArray();

        } catch (Exception e) {
            log.error("Error al generar PDF de autores", e);
            throw new RuntimeException("Error al generar PDF de autores: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] generarPdfCategorias(List<CategoriaDomain> categorias) {
        log.info("Generando PDF de categorías. Total: {}", categorias.size());

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Título
            agregarTitulo(document, "Reporte de Categorías");
            agregarFechaGeneracion(document);

            // Tabla
            float[] columnWidths = {1, 3, 6};
            Table table = new Table(UnitValue.createPercentArray(columnWidths));
            table.setWidth(UnitValue.createPercentValue(100));

            // Encabezados
            agregarEncabezadoTabla(table, new String[]{"ID", "Nombre", "Descripción"});

            // Datos
            for (CategoriaDomain categoria : categorias) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(categoria.getIdCategoria()))));
                table.addCell(new Cell().add(new Paragraph(categoria.getNombreCategoria())));
                table.addCell(new Cell().add(new Paragraph(
                        categoria.getDescripcionCategoria() != null ? categoria.getDescripcionCategoria() : "N/A")));
            }

            document.add(table);

            // Total
            agregarTotal(document, "Total de categorías: " + categorias.size());

            document.close();
            log.info("PDF de categorías generado exitosamente");
            return baos.toByteArray();

        } catch (Exception e) {
            log.error("Error al generar PDF de categorías", e);
            throw new RuntimeException("Error al generar PDF de categorías: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] generarPdfColecciones(List<ColeccionDomain> colecciones) {
        log.info("Generando PDF de colecciones. Total: {}", colecciones.size());

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Título
            agregarTitulo(document, "Reporte de Colecciones");
            agregarFechaGeneracion(document);

            // Tabla
            float[] columnWidths = {1, 3, 4, 2, 2};
            Table table = new Table(UnitValue.createPercentArray(columnWidths));
            table.setWidth(UnitValue.createPercentValue(100));

            // Encabezados
            agregarEncabezadoTabla(table, new String[]{"ID", "Nombre", "Descripción", "Fecha Inicio", "Fecha Fin"});

            // Datos
            for (ColeccionDomain coleccion : colecciones) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(coleccion.getIdColeccion()))));
                table.addCell(new Cell().add(new Paragraph(coleccion.getNombreColeccion())));
                table.addCell(new Cell().add(new Paragraph(
                        coleccion.getDescripcionColeccion() != null ? coleccion.getDescripcionColeccion() : "N/A")));
                table.addCell(new Cell().add(new Paragraph(
                        coleccion.getFechaInicio() != null ? coleccion.getFechaInicio().format(DATE_FORMATTER) : "N/A")));
                table.addCell(new Cell().add(new Paragraph(
                        coleccion.getFechaFin() != null ? coleccion.getFechaFin().format(DATE_FORMATTER) : "N/A")));
            }

            document.add(table);

            // Total
            agregarTotal(document, "Total de colecciones: " + colecciones.size());

            document.close();
            log.info("PDF de colecciones generado exitosamente");
            return baos.toByteArray();

        } catch (Exception e) {
            log.error("Error al generar PDF de colecciones", e);
            throw new RuntimeException("Error al generar PDF de colecciones: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] generarPdfObras(List<ObraDigitalDomain> obras) {
        log.info("Generando PDF de obras. Total: {}", obras.size());

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Título
            agregarTitulo(document, "Reporte de Obras Digitales");
            agregarFechaGeneracion(document);

            // Tabla
            float[] columnWidths = {1, 3, 4, 2, 1};
            Table table = new Table(UnitValue.createPercentArray(columnWidths));
            table.setWidth(UnitValue.createPercentValue(100));

            // Encabezados
            agregarEncabezadoTabla(table, new String[]{"ID", "Título", "Descripción", "Fecha Publicación", "ID Autor"});

            // Datos
            for (ObraDigitalDomain obra : obras) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(obra.getIdObraDigital()))));
                table.addCell(new Cell().add(new Paragraph(obra.getTitulo())));
                table.addCell(new Cell().add(new Paragraph(
                        obra.getDescripcion() != null ? obra.getDescripcion() : "N/A")));
                table.addCell(new Cell().add(new Paragraph(
                        obra.getFechaPublicacion() != null ? obra.getFechaPublicacion().format(DATE_FORMATTER) : "N/A")));
                table.addCell(new Cell().add(new Paragraph(
                        obra.getIdAutor() != null ? String.valueOf(obra.getIdAutor()) : "N/A")));
            }

            document.add(table);

            // Total
            agregarTotal(document, "Total de obras: " + obras.size());

            document.close();
            log.info("PDF de obras generado exitosamente");
            return baos.toByteArray();

        } catch (Exception e) {
            log.error("Error al generar PDF de obras", e);
            throw new RuntimeException("Error al generar PDF de obras: " + e.getMessage(), e);
        }
    }

    // ========== MÉTODOS AUXILIARES ==========

    private void agregarTitulo(Document document, String titulo) throws Exception {
        PdfFont fontBold = PdfFontFactory.createFont();
        Paragraph titleParagraph = new Paragraph(titulo)
                .setFont(fontBold)
                .setFontSize(18)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(10);
        document.add(titleParagraph);
    }

    private void agregarFechaGeneracion(Document document) {
        String fechaActual = LocalDate.now().format(DATE_FORMATTER);
        Paragraph fechaParagraph = new Paragraph("Fecha de generación: " + fechaActual)
                .setFontSize(10)
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginBottom(20);
        document.add(fechaParagraph);
    }

    private void agregarEncabezadoTabla(Table table, String[] headers) {
        for (String header : headers) {
            Cell cell = new Cell()
                    .add(new Paragraph(header).setBold())
                    .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                    .setTextAlignment(TextAlignment.CENTER);
            table.addHeaderCell(cell);
        }
    }

    private void agregarTotal(Document document, String textoTotal) {
        Paragraph totalParagraph = new Paragraph(textoTotal)
                .setFontSize(12)
                .setBold()
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginTop(20);
        document.add(totalParagraph);
    }
}
