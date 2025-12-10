package org.ipn.mx.galeriaarte.domain.port.out;

import org.ipn.mx.galeriaarte.domain.model.ColeccionDomain;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ColeccionRepositoryPort {
    ColeccionDomain guardar(ColeccionDomain coleccion);
    void eliminar(Integer id);
    Optional<ColeccionDomain> buscarPorId(Integer id);
    List<ColeccionDomain> buscarTodas();
    List<ColeccionDomain> buscarPorNombreContiene(String nombre);
    List<ColeccionDomain> buscarActivas(LocalDate fechaActual);
    boolean existePorId(Integer id);
}