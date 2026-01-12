package org.ipn.mx.galeriaarte.domain.port.out;

import org.ipn.mx.galeriaarte.domain.model.ArchivoDigitalDomain;
import java.util.List;
import java.util.Optional;

public interface ArchivoDigitalRepositoryPort {
    ArchivoDigitalDomain guardar(ArchivoDigitalDomain archivo);
    void eliminar(Integer id);
    Optional<ArchivoDigitalDomain> buscarPorId(Integer id);
    List<ArchivoDigitalDomain> buscarTodos();
    List<ArchivoDigitalDomain> buscarPorObraDigital(Integer idObraDigital);
    List<ArchivoDigitalDomain> buscarPorFormato(String formato);
    boolean existePorId(Integer id);
}