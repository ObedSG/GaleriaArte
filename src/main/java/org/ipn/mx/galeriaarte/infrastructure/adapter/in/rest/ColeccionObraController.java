package org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.application.dto.ColeccionObraDTO;
import org.ipn.mx.galeriaarte.application.mapper.ColeccionObraMapper;
import org.ipn.mx.galeriaarte.domain.model.ColeccionObraDomain;
import org.ipn.mx.galeriaarte.domain.port.in.ColeccionObraUseCase;
import org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/coleccion-obra")
@RequiredArgsConstructor
@Tag(name = "Colección-Obra", description = "API para gestión de relaciones entre colecciones y obras")
public class ColeccionObraController {

    private final ColeccionObraUseCase coleccionObraUseCase;
    private final ColeccionObraMapper coleccionObraMapper;

    @Operation(summary = "Agregar una obra a una colección")
    @PostMapping
    public ResponseEntity<ApiResponse<ColeccionObraDTO>> agregar(@Valid @RequestBody ColeccionObraDTO relacionDTO) {
        log.info("POST /api/coleccion-obra - Agregando obra {} a colección {}",
                relacionDTO.getIdObraDigital(), relacionDTO.getIdColeccion());

        ColeccionObraDomain domain = coleccionObraMapper.toDomain(relacionDTO);
        ColeccionObraDomain creado = coleccionObraUseCase.agregar(domain);
        ColeccionObraDTO resultado = coleccionObraMapper.toDTO(creado);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(resultado, "Obra agregada a la colección exitosamente"));
    }

    @Operation(summary = "Eliminar una relación colección-obra")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(
            @Parameter(description = "ID de la relación") @PathVariable Integer id) {
        log.info("DELETE /api/coleccion-obra/{} - Eliminando relación", id);

        coleccionObraUseCase.eliminar(id);

        return ResponseEntity.ok(ApiResponse.success(null, "Relación eliminada exitosamente"));
    }

    @Operation(summary = "Obtener una relación por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ColeccionObraDTO>> obtenerPorId(
            @Parameter(description = "ID de la relación") @PathVariable Integer id) {
        log.info("GET /api/coleccion-obra/{} - Obteniendo relación", id);

        return coleccionObraUseCase.obtenerPorId(id)
                .map(coleccionObraMapper::toDTO)
                .map(dto -> ResponseEntity.ok(ApiResponse.success(dto, "Relación encontrada")))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Relación no encontrada")));
    }

    @Operation(summary = "Obtener obras de una colección")
    @GetMapping("/coleccion/{idColeccion}")
    public ResponseEntity<ApiResponse<List<ColeccionObraDTO>>> obtenerPorColeccion(
            @Parameter(description = "ID de la colección") @PathVariable Integer idColeccion) {
        log.info("GET /api/coleccion-obra/coleccion/{} - Obteniendo obras de la colección", idColeccion);

        List<ColeccionObraDomain> relaciones = coleccionObraUseCase.obtenerPorColeccion(idColeccion);
        List<ColeccionObraDTO> resultado = coleccionObraMapper.toDTOList(relaciones);

        return ResponseEntity.ok(ApiResponse.success(resultado,
                "Se encontraron " + resultado.size() + " obras en la colección"));
    }

    @Operation(summary = "Obtener colecciones de una obra")
    @GetMapping("/obra/{idObraDigital}")
    public ResponseEntity<ApiResponse<List<ColeccionObraDTO>>> obtenerPorObra(
            @Parameter(description = "ID de la obra") @PathVariable Integer idObraDigital) {
        log.info("GET /api/coleccion-obra/obra/{} - Obteniendo colecciones de la obra", idObraDigital);

        List<ColeccionObraDomain> relaciones = coleccionObraUseCase.obtenerPorObra(idObraDigital);
        List<ColeccionObraDTO> resultado = coleccionObraMapper.toDTOList(relaciones);

        return ResponseEntity.ok(ApiResponse.success(resultado,
                "Se encontraron " + resultado.size() + " colecciones"));
    }

    @Operation(summary = "Eliminar una obra de una colección")
    @DeleteMapping("/coleccion/{idColeccion}/obra/{idObraDigital}")
    public ResponseEntity<ApiResponse<Void>> eliminarPorColeccionYObra(
            @Parameter(description = "ID de la colección") @PathVariable Integer idColeccion,
            @Parameter(description = "ID de la obra") @PathVariable Integer idObraDigital) {
        log.info("DELETE /api/coleccion-obra/coleccion/{}/obra/{} - Eliminando relación",
                idColeccion, idObraDigital);

        coleccionObraUseCase.eliminarPorColeccionYObra(idColeccion, idObraDigital);

        return ResponseEntity.ok(ApiResponse.success(null, "Obra eliminada de la colección exitosamente"));
    }
}