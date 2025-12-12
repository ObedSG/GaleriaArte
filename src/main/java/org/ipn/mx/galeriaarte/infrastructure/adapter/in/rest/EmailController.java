package org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.application.dto.EmailRequestDTO;
import org.ipn.mx.galeriaarte.domain.port.in.EmailUseCase;
import org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
@Tag(name = "Email", description = "API para envío de correos electrónicos")
public class EmailController {

    private final EmailUseCase emailUseCase;

    @Operation(summary = "Enviar un email simple",
            description = "Envía un correo electrónico simple sin adjuntos")
    @PostMapping("/enviar")
    public ResponseEntity<ApiResponse<Void>> enviarEmail(@Valid @RequestBody EmailRequestDTO emailRequest) {
        log.info("POST /api/email/enviar - Enviando email a: {}", emailRequest.getDestinatario());

        emailUseCase.enviarEmailSimple(
                emailRequest.getDestinatario(),
                emailRequest.getAsunto(),
                emailRequest.getContenido()
        );

        return ResponseEntity.ok(ApiResponse.success(null, "Email enviado exitosamente"));
    }

    @Operation(summary = "Notificar nueva obra",
            description = "Envía una notificación por email sobre una nueva obra publicada")
    @PostMapping("/notificar-obra")
    public ResponseEntity<ApiResponse<Void>> notificarNuevaObra(
            @Parameter(description = "Email del destinatario") @RequestParam String destinatario,
            @Parameter(description = "Título de la obra") @RequestParam String tituloObra,
            @Parameter(description = "Nombre del autor") @RequestParam String nombreAutor) {
        log.info("POST /api/email/notificar-obra - Notificando nueva obra a: {}", destinatario);

        emailUseCase.notificarNuevaObra(destinatario, tituloObra, nombreAutor);

        return ResponseEntity.ok(ApiResponse.success(null,
                "Notificación de nueva obra enviada exitosamente"));
    }

    @Operation(summary = "Notificar nueva colección",
            description = "Envía una notificación por email sobre una nueva colección")
    @PostMapping("/notificar-coleccion")
    public ResponseEntity<ApiResponse<Void>> notificarNuevaColeccion(
            @Parameter(description = "Email del destinatario") @RequestParam String destinatario,
            @Parameter(description = "Nombre de la colección") @RequestParam String nombreColeccion) {
        log.info("POST /api/email/notificar-coleccion - Notificando nueva colección a: {}", destinatario);

        emailUseCase.notificarNuevaColeccion(destinatario, nombreColeccion);

        return ResponseEntity.ok(ApiResponse.success(null,
                "Notificación de nueva colección enviada exitosamente"));
    }
}