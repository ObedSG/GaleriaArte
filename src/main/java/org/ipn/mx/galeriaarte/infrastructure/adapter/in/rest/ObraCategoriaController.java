package org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.application.dto.ObraCategoriaDTO;
import org.ipn.mx.galeriaarte.application.mapper.ObraCategoriaMapper;
import org.ipn.mx.galeriaarte.domain.model.ObraCategoriaDomain;
import org.ipn.mx.galeriaarte.domain.port.in.ObraCategoriaUseCase;
import org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/obra-categoria")
@RequiredArgsConstructor
@Tag(name = "Obra-Categoría", description = "API para gestión de relaciones entre obras y categorías")
public class ObraCategoriaController {

    private final ObraCategoriaUseCase obraCategoriaUseCase;
    private final ObraCategoriaMapper obraCategoriaMapper;

    @Operation(summary = "Agregar una categoría a una obra")
    @PostMapping
    public ResponseEntity<ApiResponse<ObraCategoriaDTO>> agregar(@Valid @RequestBody ObraCategoriaDTO relacionDTO) {
        log.info("POST /api/obra-categoria - Agregando categoría {} a obra {}",
                relacionDTO.getIdCategoria(), relacionDTO.getIdObraDigital());

        ObraCategoriaDomain domain = obraCategoriaMapper.toDomain(relacionDTO);
        ObraCategoriaDomain creado = obraCategoriaUseCase.agregar(domain);
        ObraCategoriaDTO resultado = obraCategoriaMapper.toDTO(creado);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(resultado, "Categoría agregada a la obra exitosamente"));
    }

    @Operation(summary = "Eliminar una relación obra-categoría")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(
            @Parameter(description = "ID de la relación") @PathVariable Integer id) {
        log.info("DELETE /api/obra-categoria/{} - Eliminando relación", id);

        obraCategoriaUseCase.eliminar(id);

        return ResponseEntity.ok(ApiResponse.success(null, "Relación eliminada exitosamente"));
    }

    @Operation(summary = "Obtener una relación por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ObraCategoriaDTO>> obtenerPorId(
            @Parameter(description = "ID de la relación") @PathVariable Integer id) {
        log.info("GET /api/obra-categoria/{} - Obteniendo relación", id);

        return obraCategoriaUseCase.obtenerPorId(id)
                .map(obraCategoriaMapper::toDTO)
                .map(dto -> ResponseEntity.ok(ApiResponse.success(dto, "Relación encontrada")))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Relación no encontrada")));
    }

    @Operation(summary = "Obtener obras de una categoría")
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<ApiResponse<List<ObraCategoriaDTO>>> obtenerPorCategoria(
            @Parameter(description = "ID de la categoría") @PathVariable Integer idCategoria) {
        log.info("GET /api/obra-categoria/categoria/{} - Obteniendo obras de la categoría", idCategoria);

        List<ObraCategoriaDomain> relaciones = obraCategoriaUseCase.obtenerPorCategoria(idCategoria);
        List<ObraCategoriaDTO> resultado = obraCategoriaMapper.toDTOList(relaciones);

        return ResponseEntity.ok(ApiResponse.success(resultado,
                "Se encontraron " + resultado.size() + " obras en la categoría"));
    }

    @Operation(summary = "Obtener categorías de una obra")
    @GetMapping("/obra/{idObraDigital}")
    public ResponseEntity<ApiResponse<List<ObraCategoriaDTO>>> obtenerPorObra(
            @Parameter(description = "ID de la obra") @PathVariable Integer idObraDigital) {
        log.info("GET /api/obra-categoria/obra/{} - Obteniendo categorías de la obra", idObraDigital);

        List<ObraCategoriaDomain> relaciones = obraCategoriaUseCase.obtenerPorObra(idObraDigital);
        List<ObraCategoriaDTO> resultado = obraCategoriaMapper.toDTOList(relaciones);

        return ResponseEntity.ok(ApiResponse.success(resultado,
                "Se encontraron " + resultado.size() + " categorías"));
    }

    @Operation(summary = "Eliminar una categoría de una obra")
    @DeleteMapping("/obra/{idObraDigital}/categoria/{idCategoria}")
    public ResponseEntity<ApiResponse<Void>> eliminarPorObraYCategoria(
            @Parameter(description = "ID de la obra") @PathVariable Integer idObraDigital,
            @Parameter(description = "ID de la categoría") @PathVariable Integer idCategoria) {
        log.info("DELETE /api/obra-categoria/obra/{}/categoria/{} - Eliminando relación",
                idObraDigital, idCategoria);

        obraCategoriaUseCase.eliminarPorObraYCategoria(idObraDigital, idCategoria);

        return ResponseEntity.ok(ApiResponse.success(null, "Categoría eliminada de la obra exitosamente"));
    }
}