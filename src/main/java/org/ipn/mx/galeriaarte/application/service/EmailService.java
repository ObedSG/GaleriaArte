package org.ipn.mx.galeriaarte.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.exception.BusinessException;
import org.ipn.mx.galeriaarte.domain.port.in.EmailUseCase;
import org.ipn.mx.galeriaarte.domain.port.out.EmailPort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService implements EmailUseCase {

    private final EmailPort emailPort;

    @Override
    public void enviarEmailSimple(String destinatario, String asunto, String contenido) {
        log.info("Enviando email simple a: {}", destinatario);

        validarParametrosEmail(destinatario, asunto, contenido);

        try {
            emailPort.enviar(destinatario, asunto, contenido);
            log.info("Email enviado exitosamente a: {}", destinatario);
        } catch (Exception e) {
            log.error("Error al enviar email a: {}", destinatario, e);
            throw new BusinessException("Error al enviar email: " + e.getMessage(), e);
        }
    }

    @Override
    public void enviarEmailConAdjunto(String destinatario, String asunto, String contenido,
                                      byte[] adjunto, String nombreAdjunto) {
        log.info("Enviando email con adjunto a: {}", destinatario);

        validarParametrosEmail(destinatario, asunto, contenido);

        if (adjunto == null || adjunto.length == 0) {
            throw new BusinessException("El adjunto no puede estar vacío");
        }

        if (nombreAdjunto == null || nombreAdjunto.trim().isEmpty()) {
            throw new BusinessException("El nombre del adjunto es obligatorio");
        }

        try {
            emailPort.enviarConAdjunto(destinatario, asunto, contenido, adjunto, nombreAdjunto);
            log.info("Email con adjunto enviado exitosamente a: {}", destinatario);
        } catch (Exception e) {
            log.error("Error al enviar email con adjunto a: {}", destinatario, e);
            throw new BusinessException("Error al enviar email con adjunto: " + e.getMessage(), e);
        }
    }

    @Override
    public void notificarNuevaObra(String destinatario, String tituloObra, String nombreAutor) {
        log.info("Notificando nueva obra '{}' a: {}", tituloObra, destinatario);

        String asunto = "Nueva obra publicada en la Galería de Arte";
        String contenido = String.format(
                "Estimado usuario,\n\n" +
                        "Se ha publicado una nueva obra en nuestra galería:\n\n" +
                        "Título: %s\n" +
                        "Autor: %s\n\n" +
                        "Te invitamos a visitarla en nuestra plataforma.\n\n" +
                        "Saludos cordiales,\n" +
                        "Equipo de Galería de Arte",
                tituloObra, nombreAutor
        );

        enviarEmailSimple(destinatario, asunto, contenido);
    }

    @Override
    public void notificarNuevaColeccion(String destinatario, String nombreColeccion) {
        log.info("Notificando nueva colección '{}' a: {}", nombreColeccion, destinatario);

        String asunto = "Nueva colección disponible en la Galería de Arte";
        String contenido = String.format(
                "Estimado usuario,\n\n" +
                        "Nos complace informarte que hemos lanzado una nueva colección:\n\n" +
                        "Colección: %s\n\n" +
                        "Explora las obras que la conforman en nuestra plataforma.\n\n" +
                        "Saludos cordiales,\n" +
                        "Equipo de Galería de Arte",
                nombreColeccion
        );

        enviarEmailSimple(destinatario, asunto, contenido);
    }

    private void validarParametrosEmail(String destinatario, String asunto, String contenido) {
        if (destinatario == null || destinatario.trim().isEmpty()) {
            throw new BusinessException("El destinatario es obligatorio");
        }

        if (!destinatario.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new BusinessException("El formato del email destinatario no es válido");
        }

        if (asunto == null || asunto.trim().isEmpty()) {
            throw new BusinessException("El asunto es obligatorio");
        }

        if (contenido == null || contenido.trim().isEmpty()) {
            throw new BusinessException("El contenido es obligatorio");
        }
    }
}