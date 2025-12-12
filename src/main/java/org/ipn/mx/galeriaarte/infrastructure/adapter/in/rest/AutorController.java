package org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.application.dto.AutorDTO;
import org.ipn.mx.galeriaarte.application.mapper.AutorMapper;
import org.ipn.mx.galeriaarte.domain.model.AutorDomain;
import org.ipn.mx.galeriaarte.domain.port.in.AutorUseCase;
import org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest.response.ApiResponse;
import org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/autores")
@RequiredArgsConstructor
@Tag(name = "Autores", description = "API para gestión de autores")
public class AutorController {

    private final AutorUseCase autorUseCase;
    private final AutorMapper autorMapper;

    @Operation(summary = "Crear un nuevo autor", description = "Crea un nuevo autor en el sistema")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Autor creado exitosamente",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping
    public ResponseEntity<ApiResponse<AutorDTO>> crear(@Valid @RequestBody AutorDTO autorDTO) {
        log.info("POST /api/autores - Creando autor: {}", autorDTO.getNombreCompleto());

        AutorDomain domain = autorMapper.toDomain(autorDTO);
        AutorDomain creado = autorUseCase.crear(domain);
        AutorDTO resultado = autorMapper.toDTO(creado);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(resultado, "Autor creado exitosamente"));
    }

    @Operation(summary = "Actualizar un autor", description = "Actualiza los datos de un autor existente")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Autor actualizado exitosamente"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Autor no encontrado"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AutorDTO>> actualizar(
            @Parameter(description = "ID del autor") @PathVariable Integer id,
            @Valid @RequestBody AutorDTO autorDTO) {
        log.info("PUT /api/autores/{} - Actualizando autor", id);

        AutorDomain domain = autorMapper.toDomain(autorDTO);
        AutorDomain actualizado = autorUseCase.actualizar(id, domain);
        AutorDTO resultado = autorMapper.toDTO(actualizado);

        return ResponseEntity.ok(ApiResponse.success(resultado, "Autor actualizado exitosamente"));
    }

    @Operation(summary = "Eliminar un autor", description = "Elimina un autor del sistema")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Autor eliminado exitosamente"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Autor no encontrado"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(
            @Parameter(description = "ID del autor") @PathVariable Integer id) {
        log.info("DELETE /api/autores/{} - Eliminando autor", id);

        autorUseCase.eliminar(id);

        return ResponseEntity.ok(ApiResponse.success(null, "Autor eliminado exitosamente"));
    }

    @Operation(summary = "Obtener un autor por ID", description = "Obtiene los datos de un autor específico")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Autor encontrado"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Autor no encontrado"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AutorDTO>> obtenerPorId(
            @Parameter(description = "ID del autor") @PathVariable Integer id) {
        log.info("GET /api/autores/{} - Obteniendo autor", id);

        return autorUseCase.obtenerPorId(id)
                .map(autorMapper::toDTO)
                .map(dto -> ResponseEntity.ok(ApiResponse.success(dto, "Autor encontrado")))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Autor no encontrado")));
    }

    @Operation(summary = "Obtener todos los autores", description = "Obtiene la lista completa de autores")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Lista de autores obtenida exitosamente"
            )
    })
    @GetMapping
    public ResponseEntity<ApiResponse<List<AutorDTO>>> obtenerTodos() {
        log.info("GET /api/autores - Obteniendo todos los autores");

        List<AutorDomain> autores = autorUseCase.obtenerTodos();
        List<AutorDTO> resultado = autorMapper.toDTOList(autores);

        return ResponseEntity.ok(ApiResponse.success(resultado,
                "Se encontraron " + resultado.size() + " autores"));
    }

    @Operation(summary = "Buscar autores por nombre", description = "Busca autores cuyo nombre contenga el texto especificado")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Búsqueda realizada exitosamente"
            )
    })
    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<List<AutorDTO>>> buscarPorNombre(
            @Parameter(description = "Texto a buscar en el nombre") @RequestParam String nombre) {
        log.info("GET /api/autores/buscar?nombre={} - Buscando autores", nombre);

        List<AutorDomain> autores = autorUseCase.buscarPorNombre(nombre);
        List<AutorDTO> resultado = autorMapper.toDTOList(autores);

        return ResponseEntity.ok(ApiResponse.success(resultado,
                "Se encontraron " + resultado.size() + " autores"));
    }
}