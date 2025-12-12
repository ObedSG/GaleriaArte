package org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.application.dto.CategoriaDTO;
import org.ipn.mx.galeriaarte.application.mapper.CategoriaMapper;
import org.ipn.mx.galeriaarte.domain.model.CategoriaDomain;
import org.ipn.mx.galeriaarte.domain.port.in.CategoriaUseCase;
import org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
@Tag(name = "Categorías", description = "API para gestión de categorías")
public class CategoriaController {

    private final CategoriaUseCase categoriaUseCase;
    private final CategoriaMapper categoriaMapper;

    @Operation(summary = "Crear una nueva categoría")
    @PostMapping
    public ResponseEntity<ApiResponse<CategoriaDTO>> crear(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        log.info("POST /api/categorias - Creando categoría: {}", categoriaDTO.getNombreCategoria());

        CategoriaDomain domain = categoriaMapper.toDomain(categoriaDTO);
        CategoriaDomain creado = categoriaUseCase.crear(domain);
        CategoriaDTO resultado = categoriaMapper.toDTO(creado);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(resultado, "Categoría creada exitosamente"));
    }

    @Operation(summary = "Actualizar una categoría")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaDTO>> actualizar(
            @Parameter(description = "ID de la categoría") @PathVariable Integer id,
            @Valid @RequestBody CategoriaDTO categoriaDTO) {
        log.info("PUT /api/categorias/{} - Actualizando categoría", id);

        CategoriaDomain domain = categoriaMapper.toDomain(categoriaDTO);
        CategoriaDomain actualizado = categoriaUseCase.actualizar(id, domain);
        CategoriaDTO resultado = categoriaMapper.toDTO(actualizado);

        return ResponseEntity.ok(ApiResponse.success(resultado, "Categoría actualizada exitosamente"));
    }

    @Operation(summary = "Eliminar una categoría")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(
            @Parameter(description = "ID de la categoría") @PathVariable Integer id) {
        log.info("DELETE /api/categorias/{} - Eliminando categoría", id);

        categoriaUseCase.eliminar(id);

        return ResponseEntity.ok(ApiResponse.success(null, "Categoría eliminada exitosamente"));
    }

    @Operation(summary = "Obtener una categoría por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaDTO>> obtenerPorId(
            @Parameter(description = "ID de la categoría") @PathVariable Integer id) {
        log.info("GET /api/categorias/{} - Obteniendo categoría", id);

        return categoriaUseCase.obtenerPorId(id)
                .map(categoriaMapper::toDTO)
                .map(dto -> ResponseEntity.ok(ApiResponse.success(dto, "Categoría encontrada")))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Categoría no encontrada")));
    }

    @Operation(summary = "Obtener todas las categorías")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoriaDTO>>> obtenerTodas() {
        log.info("GET /api/categorias - Obteniendo todas las categorías");

        List<CategoriaDomain> categorias = categoriaUseCase.obtenerTodas();
        List<CategoriaDTO> resultado = categoriaMapper.toDTOList(categorias);

        return ResponseEntity.ok(ApiResponse.success(resultado,
                "Se encontraron " + resultado.size() + " categorías"));
    }

    @Operation(summary = "Buscar categorías por nombre")
    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<List<CategoriaDTO>>> buscarPorNombre(
            @Parameter(description = "Texto a buscar en el nombre") @RequestParam String nombre) {
        log.info("GET /api/categorias/buscar?nombre={} - Buscando categorías", nombre);

        List<CategoriaDomain> categorias = categoriaUseCase.buscarPorNombre(nombre);
        List<CategoriaDTO> resultado = categoriaMapper.toDTOList(categorias);

        return ResponseEntity.ok(ApiResponse.success(resultado,
                "Se encontraron " + resultado.size() + " categorías"));
    }
}