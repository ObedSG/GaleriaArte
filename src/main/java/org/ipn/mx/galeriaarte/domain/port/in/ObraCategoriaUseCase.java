package org.ipn.mx.galeriaarte.domain.port.in;

import org.ipn.mx.galeriaarte.domain.model.ObraCategoriaDomain;
import java.util.List;
import java.util.Optional;

public interface ObraCategoriaUseCase {
    ObraCategoriaDomain agregar(ObraCategoriaDomain relacion);
    void eliminar(Integer id);
    Optional<ObraCategoriaDomain> obtenerPorId(Integer id);
    List<ObraCategoriaDomain> obtenerPorCategoria(Integer idCategoria);
    List<ObraCategoriaDomain> obtenerPorObra(Integer idObraDigital);
    void eliminarPorObraYCategoria(Integer idObraDigital, Integer idCategoria);
}