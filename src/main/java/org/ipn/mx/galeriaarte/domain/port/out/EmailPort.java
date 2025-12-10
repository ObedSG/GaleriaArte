package org.ipn.mx.galeriaarte.domain.port.out;

public interface EmailPort {
    void enviar(String destinatario, String asunto, String contenido);
    void enviarConAdjunto(String destinatario, String asunto, String contenido, byte[] adjunto, String nombreAdjunto);
}