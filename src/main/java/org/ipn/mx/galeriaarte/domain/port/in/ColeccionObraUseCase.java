package org.ipn.mx.galeriaarte.domain.port.in;

import org.ipn.mx.galeriaarte.domain.model.ColeccionObraDomain;
import java.util.List;
import java.util.Optional;

public interface ColeccionObraUseCase {
    ColeccionObraDomain agregar(ColeccionObraDomain relacion);
    void eliminar(Integer id);
    Optional<ColeccionObraDomain> obtenerPorId(Integer id);
    List<ColeccionObraDomain> obtenerPorColeccion(Integer idColeccion);
    List<ColeccionObraDomain> obtenerPorObra(Integer idObraDigital);
    void eliminarPorColeccionYObra(Integer idColeccion, Integer idObraDigital);
}