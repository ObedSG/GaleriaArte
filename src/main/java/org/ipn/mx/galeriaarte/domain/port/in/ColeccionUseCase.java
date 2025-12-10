package org.ipn.mx.galeriaarte.domain.port.in;

import org.ipn.mx.galeriaarte.domain.model.ColeccionDomain;
import java.util.List;
import java.util.Optional;

public interface ColeccionUseCase {
    ColeccionDomain crear(ColeccionDomain coleccion);
    ColeccionDomain actualizar(Integer id, ColeccionDomain coleccion);
    void eliminar(Integer id);
    Optional<ColeccionDomain> obtenerPorId(Integer id);
    List<ColeccionDomain> obtenerTodas();
    List<ColeccionDomain> buscarPorNombre(String nombre);
    List<ColeccionDomain> obtenerColeccionesActivas();
}