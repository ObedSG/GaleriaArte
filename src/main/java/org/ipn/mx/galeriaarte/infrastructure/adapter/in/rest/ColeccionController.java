package org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.application.dto.ColeccionDTO;
import org.ipn.mx.galeriaarte.application.mapper.ColeccionMapper;
import org.ipn.mx.galeriaarte.domain.model.ColeccionDomain;
import org.ipn.mx.galeriaarte.domain.port.in.ColeccionUseCase;
import org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/colecciones")
@RequiredArgsConstructor
@Tag(name = "Colecciones", description = "API para gestión de colecciones")
public class ColeccionController {

    private final ColeccionUseCase coleccionUseCase;
    private final ColeccionMapper coleccionMapper;

    @Operation(summary = "Crear una nueva colección")
    @PostMapping
    public ResponseEntity<ApiResponse<ColeccionDTO>> crear(@Valid @RequestBody ColeccionDTO coleccionDTO) {
        log.info("POST /api/colecciones - Creando colección: {}", coleccionDTO.getNombreColeccion());

        ColeccionDomain domain = coleccionMapper.toDomain(coleccionDTO);
        ColeccionDomain creado = coleccionUseCase.crear(domain);
        ColeccionDTO resultado = coleccionMapper.toDTO(creado);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(resultado, "Colección creada exitosamente"));
    }

    @Operation(summary = "Actualizar una colección")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ColeccionDTO>> actualizar(
            @Parameter(description = "ID de la colección") @PathVariable Integer id,
            @Valid @RequestBody ColeccionDTO coleccionDTO) {
        log.info("PUT /api/colecciones/{} - Actualizando colección", id);

        ColeccionDomain domain = coleccionMapper.toDomain(coleccionDTO);
        ColeccionDomain actualizado = coleccionUseCase.actualizar(id, domain);
        ColeccionDTO resultado = coleccionMapper.toDTO(actualizado);

        return ResponseEntity.ok(ApiResponse.success(resultado, "Colección actualizada exitosamente"));
    }

    @Operation(summary = "Eliminar una colección")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(
            @Parameter(description = "ID de la colección") @PathVariable Integer id) {
        log.info("DELETE /api/colecciones/{} - Eliminando colección", id);

        coleccionUseCase.eliminar(id);

        return ResponseEntity.ok(ApiResponse.success(null, "Colección eliminada exitosamente"));
    }

    @Operation(summary = "Obtener una colección por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ColeccionDTO>> obtenerPorId(
            @Parameter(description = "ID de la colección") @PathVariable Integer id) {
        log.info("GET /api/colecciones/{} - Obteniendo colección", id);

        return coleccionUseCase.obtenerPorId(id)
                .map(coleccionMapper::toDTO)
                .map(dto -> ResponseEntity.ok(ApiResponse.success(dto, "Colección encontrada")))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Colección no encontrada")));
    }

    @Operation(summary = "Obtener todas las colecciones")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ColeccionDTO>>> obtenerTodas() {
        log.info("GET /api/colecciones - Obteniendo todas las colecciones");

        List<ColeccionDomain> colecciones = coleccionUseCase.obtenerTodas();
        List<ColeccionDTO> resultado = coleccionMapper.toDTOList(colecciones);

        return ResponseEntity.ok(ApiResponse.success(resultado,
                "Se encontraron " + resultado.size() + " colecciones"));
    }

    @Operation(summary = "Buscar colecciones por nombre")
    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<List<ColeccionDTO>>> buscarPorNombre(
            @Parameter(description = "Texto a buscar en el nombre") @RequestParam String nombre) {
        log.info("GET /api/colecciones/buscar?nombre={} - Buscando colecciones", nombre);

        List<ColeccionDomain> colecciones = coleccionUseCase.buscarPorNombre(nombre);
        List<ColeccionDTO> resultado = coleccionMapper.toDTOList(colecciones);

        return ResponseEntity.ok(ApiResponse.success(resultado,
                "Se encontraron " + resultado.size() + " colecciones"));
    }

    @Operation(summary = "Obtener colecciones activas")
    @GetMapping("/activas")
    public ResponseEntity<ApiResponse<List<ColeccionDTO>>> obtenerActivas() {
        log.info("GET /api/colecciones/activas - Obteniendo colecciones activas");

        List<ColeccionDomain> colecciones = coleccionUseCase.obtenerColeccionesActivas();
        List<ColeccionDTO> resultado = coleccionMapper.toDTOList(colecciones);

        return ResponseEntity.ok(ApiResponse.success(resultado,
                "Se encontraron " + resultado.size() + " colecciones activas"));
    }
}