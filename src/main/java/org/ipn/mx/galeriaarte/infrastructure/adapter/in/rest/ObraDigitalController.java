package org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.application.dto.ObraDigitalDTO;
import org.ipn.mx.galeriaarte.application.mapper.ObraDigitalMapper;
import org.ipn.mx.galeriaarte.domain.model.ObraDigitalDomain;
import org.ipn.mx.galeriaarte.domain.port.in.ObraDigitalUseCase;
import org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest.response.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/obras")
@RequiredArgsConstructor
@Tag(name = "Obras Digitales", description = "API para gestión de obras digitales")
public class ObraDigitalController {

    private final ObraDigitalUseCase obraDigitalUseCase;
    private final ObraDigitalMapper obraDigitalMapper;

    @Operation(summary = "Crear una nueva obra digital")
    @PostMapping
    public ResponseEntity<ApiResponse<ObraDigitalDTO>> crear(@Valid @RequestBody ObraDigitalDTO obraDTO) {
        log.info("POST /api/obras - Creando obra: {}", obraDTO.getTitulo());

        ObraDigitalDomain domain = obraDigitalMapper.toDomain(obraDTO);
        ObraDigitalDomain creado = obraDigitalUseCase.crear(domain);
        ObraDigitalDTO resultado = obraDigitalMapper.toDTO(creado);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(resultado, "Obra creada exitosamente"));
    }

    @Operation(summary = "Actualizar una obra digital")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ObraDigitalDTO>> actualizar(
            @Parameter(description = "ID de la obra") @PathVariable Integer id,
            @Valid @RequestBody ObraDigitalDTO obraDTO) {
        log.info("PUT /api/obras/{} - Actualizando obra", id);

        ObraDigitalDomain domain = obraDigitalMapper.toDomain(obraDTO);
        ObraDigitalDomain actualizado = obraDigitalUseCase.actualizar(id, domain);
        ObraDigitalDTO resultado = obraDigitalMapper.toDTO(actualizado);

        return ResponseEntity.ok(ApiResponse.success(resultado, "Obra actualizada exitosamente"));
    }

    @Operation(summary = "Eliminar una obra digital")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(
            @Parameter(description = "ID de la obra") @PathVariable Integer id) {
        log.info("DELETE /api/obras/{} - Eliminando obra", id);

        obraDigitalUseCase.eliminar(id);

        return ResponseEntity.ok(ApiResponse.success(null, "Obra eliminada exitosamente"));
    }

    @Operation(summary = "Obtener una obra digital por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ObraDigitalDTO>> obtenerPorId(
            @Parameter(description = "ID de la obra") @PathVariable Integer id) {
        log.info("GET /api/obras/{} - Obteniendo obra", id);

        return obraDigitalUseCase.obtenerPorId(id)
                .map(obraDigitalMapper::toDTO)
                .map(dto -> ResponseEntity.ok(ApiResponse.success(dto, "Obra encontrada")))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Obra no encontrada")));
    }

    @Operation(summary = "Obtener todas las obras digitales")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ObraDigitalDTO>>> obtenerTodas() {
        log.info("GET /api/obras - Obteniendo todas las obras");

        List<ObraDigitalDomain> obras = obraDigitalUseCase.obtenerTodas();
        List<ObraDigitalDTO> resultado = obraDigitalMapper.toDTOList(obras);

        return ResponseEntity.ok(ApiResponse.success(resultado,
                "Se encontraron " + resultado.size() + " obras"));
    }

    @Operation(summary = "Buscar obras por título")
    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<List<ObraDigitalDTO>>> buscarPorTitulo(
            @Parameter(description = "Texto a buscar en el título") @RequestParam String titulo) {
        log.info("GET /api/obras/buscar?titulo={} - Buscando obras", titulo);

        List<ObraDigitalDomain> obras = obraDigitalUseCase.buscarPorTitulo(titulo);
        List<ObraDigitalDTO> resultado = obraDigitalMapper.toDTOList(obras);

        return ResponseEntity.ok(ApiResponse.success(resultado,
                "Se encontraron " + resultado.size() + " obras"));
    }

    @Operation(summary = "Obtener obras por autor")
    @GetMapping("/autor/{idAutor}")
    public ResponseEntity<ApiResponse<List<ObraDigitalDTO>>> obtenerPorAutor(
            @Parameter(description = "ID del autor") @PathVariable Integer idAutor) {
        log.info("GET /api/obras/autor/{} - Obteniendo obras del autor", idAutor);

        List<ObraDigitalDomain> obras = obraDigitalUseCase.obtenerPorAutor(idAutor);
        List<ObraDigitalDTO> resultado = obraDigitalMapper.toDTOList(obras);

        return ResponseEntity.ok(ApiResponse.success(resultado,
                "Se encontraron " + resultado.size() + " obras del autor"));
    }

    @Operation(summary = "Obtener obras por categoría")
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<ApiResponse<List<ObraDigitalDTO>>> obtenerPorCategoria(
            @Parameter(description = "ID de la categoría") @PathVariable Integer idCategoria) {
        log.info("GET /api/obras/categoria/{} - Obteniendo obras de la categoría", idCategoria);

        List<ObraDigitalDomain> obras = obraDigitalUseCase.obtenerPorCategoria(idCategoria);
        List<ObraDigitalDTO> resultado = obraDigitalMapper.toDTOList(obras);

        return ResponseEntity.ok(ApiResponse.success(resultado,
                "Se encontraron " + resultado.size() + " obras de la categoría"));
    }

    @Operation(summary = "Obtener obras por colección")
    @GetMapping("/coleccion/{idColeccion}")
    public ResponseEntity<ApiResponse<List<ObraDigitalDTO>>> obtenerPorColeccion(
            @Parameter(description = "ID de la colección") @PathVariable Integer idColeccion) {
        log.info("GET /api/obras/coleccion/{} - Obteniendo obras de la colección", idColeccion);

        List<ObraDigitalDomain> obras = obraDigitalUseCase.obtenerPorColeccion(idColeccion);
        List<ObraDigitalDTO> resultado = obraDigitalMapper.toDTOList(obras);

        return ResponseEntity.ok(ApiResponse.success(resultado,
                "Se encontraron " + resultado.size() + " obras de la colección"));
    }

    @Operation(summary = "Obtener obras por rango de fechas")
    @GetMapping("/rango-fechas")
    public ResponseEntity<ApiResponse<List<ObraDigitalDTO>>> obtenerPorRangoFechas(
            @Parameter(description = "Fecha de inicio")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @Parameter(description = "Fecha de fin")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        log.info("GET /api/obras/rango-fechas?fechaInicio={}&fechaFin={}", fechaInicio, fechaFin);

        List<ObraDigitalDomain> obras = obraDigitalUseCase.obtenerPorRangoFechas(fechaInicio, fechaFin);
        List<ObraDigitalDTO> resultado = obraDigitalMapper.toDTOList(obras);

        return ResponseEntity.ok(ApiResponse.success(resultado,
                "Se encontraron " + resultado.size() + " obras en el rango de fechas"));
    }
}