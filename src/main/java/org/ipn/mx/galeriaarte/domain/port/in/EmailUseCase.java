package org.ipn.mx.galeriaarte.domain.port.in;

public interface EmailUseCase {
    void enviarEmailSimple(String destinatario, String asunto, String contenido);
    void enviarEmailConAdjunto(String destinatario, String asunto, String contenido, byte[] adjunto, String nombreAdjunto);
    void notificarNuevaObra(String destinatario, String tituloObra, String nombreAutor);
    void notificarNuevaColeccion(String destinatario, String nombreColeccion);
}