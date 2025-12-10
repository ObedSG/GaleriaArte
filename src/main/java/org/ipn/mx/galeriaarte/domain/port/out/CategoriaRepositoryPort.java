package org.ipn.mx.galeriaarte.domain.port.out;

import org.ipn.mx.galeriaarte.domain.model.CategoriaDomain;
import java.util.List;
import java.util.Optional;

public interface CategoriaRepositoryPort {
    CategoriaDomain guardar(CategoriaDomain categoria);
    void eliminar(Integer id);
    Optional<CategoriaDomain> buscarPorId(Integer id);
    List<CategoriaDomain> buscarTodas();
    List<CategoriaDomain> buscarPorNombreContiene(String nombre);
    boolean existePorId(Integer id);
}