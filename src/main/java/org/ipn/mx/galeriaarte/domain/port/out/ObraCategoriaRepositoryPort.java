package org.ipn.mx.galeriaarte.domain.port.out;

import org.ipn.mx.galeriaarte.domain.model.ObraCategoriaDomain;
import java.util.List;
import java.util.Optional;

public interface ObraCategoriaRepositoryPort {
    ObraCategoriaDomain guardar(ObraCategoriaDomain relacion);
    void eliminar(Integer id);
    Optional<ObraCategoriaDomain> buscarPorId(Integer id);
    List<ObraCategoriaDomain> buscarPorCategoria(Integer idCategoria);
    List<ObraCategoriaDomain> buscarPorObra(Integer idObraDigital);
    void eliminarPorObraYCategoria(Integer idObraDigital, Integer idCategoria);
    boolean existeRelacion(Integer idObraDigital, Integer idCategoria);
}