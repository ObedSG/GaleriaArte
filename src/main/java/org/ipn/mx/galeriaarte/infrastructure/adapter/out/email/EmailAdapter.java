//package org.ipn.mx.galeriaarte.infrastructure.adapter.out.email;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.ipn.mx.galeriaarte.domain.port.out.EmailPort;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class EmailAdapter implements EmailPort {
//
//    private final JavaMailSender mailSender;
//
//    @Value("${spring.mail.username}")
//    private String remitente;
//
//    @Override
//    public void enviar(String destinatario, String asunto, String contenido) {
//        log.info("Enviando email simple a: {}", destinatario);
//
//        try {
//            SimpleMailMessage mensaje = new SimpleMailMessage();
//            mensaje.setFrom(remitente);
//            mensaje.setTo(destinatario);
//            mensaje.setSubject(asunto);
//            mensaje.setText(contenido);
//
//            mailSender.send(mensaje);
//            log.info("Email enviado exitosamente a: {}", destinatario);
//
//        } catch (Exception e) {
//            log.error("Error al enviar email a: {}", destinatario, e);
//            throw new RuntimeException("Error al enviar email: " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public void enviarConAdjunto(String destinatario, String asunto, String contenido,
//                                 byte[] adjunto, String nombreAdjunto) {
//        log.info("Enviando email con adjunto a: {}", destinatario);
//
//        try {
//            MimeMessage mensaje = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");
//
//            helper.setFrom(remitente);
//            helper.setTo(destinatario);
//            helper.setSubject(asunto);
//            helper.setText(contenido);
//
//            // Agregar adjunto
//            ByteArrayResource adjuntoResource = new ByteArrayResource(adjunto);
//            helper.addAttachment(nombreAdjunto, adjuntoResource);
//
//            mailSender.send(mensaje);
//            log.info("Email con adjunto enviado exitosamente a: {}", destinatario);
//
//        } catch (MessagingException e) {
//            log.error("Error al enviar email con adjunto a: {}", destinatario, e);
//            throw new RuntimeException("Error al enviar email con adjunto: " + e.getMessage(), e);
//        }
//    }
//}

package org.ipn.mx.galeriaarte.infrastructure.adapter.out.email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.port.out.EmailPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
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
        log.info("Enviando email vía BREVO API a {}", destinatario);

        String url = "https://api.brevo.com/v3/smtp/email";

        try {
            // Headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("api-key", apiKey);

            // Body
            Map<String, Object> body = new HashMap<>();

            Map<String, String> sender = new HashMap<>();
            sender.put("email", senderEmail);
            sender.put("name", senderName);

            Map<String, String> to = new HashMap<>();
            to.put("email", destinatario);

            body.put("sender", sender);
            body.put("to", new Map[]{to});
            body.put("subject", asunto);
            body.put("textContent", contenido);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

            restTemplate.postForEntity(url, request, String.class);

            log.info("Email enviado correctamente vía BREVO a {}", destinatario);

        } catch (Exception e) {
            log.error("Error enviando email vía BREVO: {}", e.getMessage());
            throw new RuntimeException("Error enviando email vía Brevo: " + e.getMessage());
        }
    }

    @Override
    public void enviarConAdjunto(String destinatario, String asunto, String contenido,
                                 byte[] adjunto, String nombreAdjunto) {
        throw new UnsupportedOperationException("Adjuntos aún no implementados con Brevo API");
    }
}
