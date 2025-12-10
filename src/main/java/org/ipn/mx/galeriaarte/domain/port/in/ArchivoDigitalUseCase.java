package org.ipn.mx.galeriaarte.domain.port.in;

import org.ipn.mx.galeriaarte.domain.model.ArchivoDigitalDomain;
import java.util.List;
import java.util.Optional;

public interface ArchivoDigitalUseCase {
    ArchivoDigitalDomain crear(ArchivoDigitalDomain archivo);
    ArchivoDigitalDomain actualizar(Integer id, ArchivoDigitalDomain archivo);
    void eliminar(Integer id);
    Optional<ArchivoDigitalDomain> obtenerPorId(Integer id);
    List<ArchivoDigitalDomain> obtenerPorObraDigital(Integer idObraDigital);
    List<ArchivoDigitalDomain> obtenerPorFormato(String formato);
}