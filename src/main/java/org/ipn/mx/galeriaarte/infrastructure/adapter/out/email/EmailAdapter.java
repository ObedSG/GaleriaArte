package org.ipn.mx.galeriaarte.infrastructure.adapter.out.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.port.out.EmailPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailAdapter implements EmailPort {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String remitente;

    @Override
    public void enviar(String destinatario, String asunto, String contenido) {
        log.info("Enviando email simple a: {}", destinatario);

        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setFrom(remitente);
            mensaje.setTo(destinatario);
            mensaje.setSubject(asunto);
            mensaje.setText(contenido);

            mailSender.send(mensaje);
            log.info("Email enviado exitosamente a: {}", destinatario);

        } catch (Exception e) {
            log.error("Error al enviar email a: {}", destinatario, e);
            throw new RuntimeException("Error al enviar email: " + e.getMessage(), e);
        }
    }

    @Override
    public void enviarConAdjunto(String destinatario, String asunto, String contenido,
                                 byte[] adjunto, String nombreAdjunto) {
        log.info("Enviando email con adjunto a: {}", destinatario);

        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

            helper.setFrom(remitente);
            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setText(contenido);

            // Agregar adjunto
            ByteArrayResource adjuntoResource = new ByteArrayResource(adjunto);
            helper.addAttachment(nombreAdjunto, adjuntoResource);

            mailSender.send(mensaje);
            log.info("Email con adjunto enviado exitosamente a: {}", destinatario);

        } catch (MessagingException e) {
            log.error("Error al enviar email con adjunto a: {}", destinatario, e);
            throw new RuntimeException("Error al enviar email con adjunto: " + e.getMessage(), e);
        }
    }
}