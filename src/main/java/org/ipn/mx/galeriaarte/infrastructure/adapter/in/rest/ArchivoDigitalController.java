package org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.application.dto.ArchivoDigitalDTO;
import org.ipn.mx.galeriaarte.application.mapper.ArchivoDigitalMapper;
import org.ipn.mx.galeriaarte.domain.model.ArchivoDigitalDomain;
import org.ipn.mx.galeriaarte.domain.port.in.ArchivoDigitalUseCase;
import org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/archivos")
@RequiredArgsConstructor
@Tag(name = "Archivos Digitales", description = "API para gestión de archivos digitales")
public class ArchivoDigitalController {

    private final ArchivoDigitalUseCase archivoDigitalUseCase;
    private final ArchivoDigitalMapper archivoDigitalMapper;

    @Operation(summary = "Crear un nuevo archivo digital")
    @PostMapping
    public ResponseEntity<ApiResponse<ArchivoDigitalDTO>> crear(@Valid @RequestBody ArchivoDigitalDTO archivoDTO) {
        log.info("POST /api/archivos - Creando archivo: {}", archivoDTO.getRuta());

        ArchivoDigitalDomain domain = archivoDigitalMapper.toDomain(archivoDTO);
        ArchivoDigitalDomain creado = archivoDigitalUseCase.crear(domain);
        ArchivoDigitalDTO resultado = archivoDigitalMapper.toDTO(creado);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(resultado, "Archivo creado exitosamente"));
    }

    @Operation(summary = "Actualizar un archivo digital")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ArchivoDigitalDTO>> actualizar(
            @Parameter(description = "ID del archivo") @PathVariable Integer id,
            @Valid @RequestBody ArchivoDigitalDTO archivoDTO) {
        log.info("PUT /api/archivos/{} - Actualizando archivo", id);

        ArchivoDigitalDomain domain = archivoDigitalMapper.toDomain(archivoDTO);
        ArchivoDigitalDomain actualizado = archivoDigitalUseCase.actualizar(id, domain);
        ArchivoDigitalDTO resultado = archivoDigitalMapper.toDTO(actualizado);

        return ResponseEntity.ok(ApiResponse.success(resultado, "Archivo actualizado exitosamente"));
    }

    @Operation(summary = "Eliminar un archivo digital")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(
            @Parameter(description = "ID del archivo") @PathVariable Integer id) {
        log.info("DELETE /api/archivos/{} - Eliminando archivo", id);

        archivoDigitalUseCase.eliminar(id);

        return ResponseEntity.ok(ApiResponse.success(null, "Archivo eliminado exitosamente"));
    }

    @Operation(summary = "Obtener un archivo digital por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ArchivoDigitalDTO>> obtenerPorId(
            @Parameter(description = "ID del archivo") @PathVariable Integer id) {
        log.info("GET /api/archivos/{} - Obteniendo archivo", id);

        return archivoDigitalUseCase.obtenerPorId(id)
                .map(archivoDigitalMapper::toDTO)
                .map(dto -> ResponseEntity.ok(ApiResponse.success(dto, "Archivo encontrado")))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Archivo no encontrado")));
    }

    @Operation(summary = "Obtener archivos por obra digital")
    @GetMapping("/obra/{idObraDigital}")
    public ResponseEntity<ApiResponse<List<ArchivoDigitalDTO>>> obtenerPorObraDigital(
            @Parameter(description = "ID de la obra digital") @PathVariable Integer idObraDigital) {
        log.info("GET /api/archivos/obra/{} - Obteniendo archivos de la obra", idObraDigital);

        List<ArchivoDigitalDomain> archivos = archivoDigitalUseCase.obtenerPorObraDigital(idObraDigital);
        List<ArchivoDigitalDTO> resultado = archivoDigitalMapper.toDTOList(archivos);

        return ResponseEntity.ok(ApiResponse.success(resultado,
                "Se encontraron " + resultado.size() + " archivos"));
    }

    @Operation(summary = "Obtener archivos por formato")
    @GetMapping("/formato/{formato}")
    public ResponseEntity<ApiResponse<List<ArchivoDigitalDTO>>> obtenerPorFormato(
            @Parameter(description = "Formato del archivo") @PathVariable String formato) {
        log.info("GET /api/archivos/formato/{} - Obteniendo archivos por formato", formato);

        List<ArchivoDigitalDomain> archivos = archivoDigitalUseCase.obtenerPorFormato(formato);
        List<ArchivoDigitalDTO> resultado = archivoDigitalMapper.toDTOList(archivos);

        return ResponseEntity.ok(ApiResponse.success(resultado,
                "Se encontraron " + resultado.size() + " archivos"));
    }
}