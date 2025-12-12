package org.ipn.mx.galeriaarte.infrastructure.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.exception.BusinessException;
import org.ipn.mx.galeriaarte.domain.exception.EntityNotFoundException;
import org.ipn.mx.galeriaarte.domain.exception.ValidationException;
import org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest.response.ApiResponse;
import org.ipn.mx.galeriaarte.infrastructure.adapter.in.rest.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Verifica si la ruta debe ser ignorada por el manejador de excepciones
     */
    private boolean shouldIgnorePath(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.contains("/swagger") ||
                path.contains("/api-docs") ||
                path.contains("/v3/api-docs") ||
                path.contains("/webjars") ||
                path.contains("/swagger-ui") ||
                path.contains("/swagger-resources") ||
                path.contains("/favicon.ico");
    }

    /**
     * Maneja excepciones de recurso no encontrado
     */
    @ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFound(
            org.springframework.web.servlet.resource.NoResourceFoundException ex,
            HttpServletRequest request) {

        // Ignorar recursos estáticos y de Swagger
        if (shouldIgnorePath(request)) {
            return null;
        }

        log.warn("Recurso no encontrado: {}", request.getRequestURI());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("Recurso no encontrado")
                .errors(List.of("El recurso solicitado no existe"))
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    /**
     * Maneja excepciones de entidad no encontrada
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleEntityNotFoundException(
            EntityNotFoundException ex, HttpServletRequest request) {
        if (shouldIgnorePath(request)) {
            return null;
        }

        log.error("Entidad no encontrada: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));
    }

    /**
     * Maneja excepciones de validación de dominio
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            ValidationException ex, HttpServletRequest request) {
        if (shouldIgnorePath(request)) {
            return null;
        }

        log.error("Error de validación: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Error de validación")
                .errors(List.of(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    /**
     * Maneja excepciones de negocio
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(
            BusinessException ex, HttpServletRequest request) {
        if (shouldIgnorePath(request)) {
            return null;
        }

        log.error("Error de negocio: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage()));
    }

    /**
     * Maneja excepciones de validación de Spring (anotaciones @Valid)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        if (shouldIgnorePath(request)) {
            return null;
        }

        log.error("Error de validación de campos: {}", ex.getMessage());

        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(fieldName + ": " + errorMessage);
        });

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Error de validación en los campos")
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    /**
     * Maneja excepciones de argumento ilegal
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(
            IllegalArgumentException ex, HttpServletRequest request) {
        if (shouldIgnorePath(request)) {
            return null;
        }

        log.error("Argumento inválido: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage()));
    }

    /**
     * Maneja excepciones de estado ilegal
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalStateException(
            IllegalStateException ex, HttpServletRequest request) {
        if (shouldIgnorePath(request)) {
            return null;
        }

        log.error("Estado ilegal: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(ex.getMessage()));
    }

    /**
     * Maneja cualquier otra excepción no controlada
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(
            Exception ex, HttpServletRequest request) {
        if (shouldIgnorePath(request)) {
            return null;
        }

        log.error("Error inesperado: ", ex);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Ha ocurrido un error inesperado en el servidor")
                .errors(List.of(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    /**
     * Maneja excepciones de runtime
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleRuntimeException(
            RuntimeException ex, HttpServletRequest request) {
        if (shouldIgnorePath(request)) {
            return null;
        }

        log.error("Error de runtime: {}", ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Error interno del servidor: " + ex.getMessage()));
    }
}