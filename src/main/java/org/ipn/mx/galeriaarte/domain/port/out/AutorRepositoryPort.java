package org.ipn.mx.galeriaarte.domain.port.out;

import org.ipn.mx.galeriaarte.domain.model.AutorDomain;
import java.util.List;
import java.util.Optional;

public interface AutorRepositoryPort {
    AutorDomain guardar(AutorDomain autor);
    void eliminar(Integer id);
    Optional<AutorDomain> buscarPorId(Integer id);
    List<AutorDomain> buscarTodos();
    List<AutorDomain> buscarPorNombreContiene(String nombre);
    boolean existePorId(Integer id);
}