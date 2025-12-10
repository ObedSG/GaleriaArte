package org.ipn.mx.galeriaarte.domain.port.in;

import org.ipn.mx.galeriaarte.domain.model.AutorDomain;
import java.util.List;
import java.util.Optional;

public interface AutorUseCase {
    AutorDomain crear(AutorDomain autor);
    AutorDomain actualizar(Integer id, AutorDomain autor);
    void eliminar(Integer id);
    Optional<AutorDomain> obtenerPorId(Integer id);
    List<AutorDomain> obtenerTodos();
    List<AutorDomain> buscarPorNombre(String nombre);
}