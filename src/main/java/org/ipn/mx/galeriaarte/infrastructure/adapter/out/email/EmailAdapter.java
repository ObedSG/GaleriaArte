package org.ipn.mx.galeriaarte.infrastructure.adapter.out.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.port.out.EmailPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailAdapter implements EmailPort {

    @Value("${brevo.api.key}")
    private String apiKey;

    @Value("${brevo.sender.email}")
    private String senderEmail;

    @Value("${brevo.sender.name}")
    private String senderName;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void enviar(String destinatario, String asunto, String contenido) {
        log.info("Enviando email vía BREVO API a: {}", destinatario);

        String url = "https://api.brevo.com/v3/smtp/email";

        try {
            // Validar configuración
            if (apiKey == null || apiKey.isEmpty() || apiKey.equals("${BREVO_API_KEY}")) {
                throw new RuntimeException("BREVO_API_KEY no está configurada correctamente");
            }

            // Headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("api-key", apiKey);
            headers.set("accept", "application/json");

            // Body
            Map<String, Object> body = new HashMap<>();

            // Sender
            Map<String, String> sender = new HashMap<>();
            sender.put("email", senderEmail);
            sender.put("name", senderName);
            body.put("sender", sender);

            // Recipient
            Map<String, String> to = new HashMap<>();
            to.put("email", destinatario);
            body.put("to", new Map[]{to});

            // Content
            body.put("subject", asunto);
            body.put("textContent", contenido);
            body.put("htmlContent", "<html><body><p>" + contenido + "</p></body></html>");

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

            log.debug("Enviando request a Brevo: {}", url);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            log.info("Email enviado exitosamente vía BREVO a: {}. Response: {}",
                    destinatario, response.getStatusCode());

        } catch (HttpClientErrorException e) {
            log.error("Error HTTP al enviar email vía BREVO. Status: {}, Body: {}",
                    e.getStatusCode(), e.getResponseBodyAsString());

            String errorMsg = switch (e.getStatusCode().value()) {
                case 401 -> "API Key inválida o no configurada. Verifica BREVO_API_KEY en las variables de entorno.";
                case 403 -> "Email remitente no verificado en Brevo. Verifica tu sender en https://app.brevo.com";
                case 400 -> "Datos inválidos en la petición: " + e.getResponseBodyAsString();
                default -> "Error al enviar email: " + e.getMessage();
            };

            throw new RuntimeException(errorMsg, e);

        } catch (Exception e) {
            log.error("Error inesperado al enviar email vía BREVO: {}", e.getMessage(), e);
            throw new RuntimeException("Error al enviar email: " + e.getMessage(), e);
        }
    }

    @Override
    public void enviarConAdjunto(String destinatario, String asunto, String contenido,
                                 byte[] adjunto, String nombreAdjunto) {
        log.warn("Envío de adjuntos no implementado con Brevo API REST");
        throw new UnsupportedOperationException(
                "El envío de adjuntos con Brevo API REST requiere implementación adicional. " +
                        "Usa SMTP para adjuntos o implementa multipart/form-data."
        );
    }
}