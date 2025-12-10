package org.ipn.mx.galeriaarte.domain.port.in;

import org.ipn.mx.galeriaarte.domain.model.CategoriaDomain;
import java.util.List;
import java.util.Optional;

public interface CategoriaUseCase {
    CategoriaDomain crear(CategoriaDomain categoria);
    CategoriaDomain actualizar(Integer id, CategoriaDomain categoria);
    void eliminar(Integer id);
    Optional<CategoriaDomain> obtenerPorId(Integer id);
    List<CategoriaDomain> obtenerTodas();
    List<CategoriaDomain> buscarPorNombre(String nombre);
}