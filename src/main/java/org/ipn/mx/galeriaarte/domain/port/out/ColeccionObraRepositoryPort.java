package org.ipn.mx.galeriaarte.domain.port.out;

import org.ipn.mx.galeriaarte.domain.model.ColeccionObraDomain;
import java.util.List;
import java.util.Optional;

public interface ColeccionObraRepositoryPort {
    ColeccionObraDomain guardar(ColeccionObraDomain relacion);
    void eliminar(Integer id);
    Optional<ColeccionObraDomain> buscarPorId(Integer id);
    List<ColeccionObraDomain> buscarPorColeccion(Integer idColeccion);
    List<ColeccionObraDomain> buscarPorObra(Integer idObraDigital);
    void eliminarPorColeccionYObra(Integer idColeccion, Integer idObraDigital);
    boolean existeRelacion(Integer idColeccion, Integer idObraDigital);
}