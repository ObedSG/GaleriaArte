package org.ipn.mx.galeriaarte.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.exception.EntityNotFoundException;
import org.ipn.mx.galeriaarte.domain.model.CategoriaDomain;
import org.ipn.mx.galeriaarte.domain.port.in.CategoriaUseCase;
import org.ipn.mx.galeriaarte.domain.port.out.CategoriaRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaService implements CategoriaUseCase {

    private final CategoriaRepositoryPort categoriaRepositoryPort;

    @Override
    public CategoriaDomain crear(CategoriaDomain categoria) {
        log.info("Creando nueva categoría: {}", categoria.getNombreCategoria());
        categoria.validar();
        return categoriaRepositoryPort.guardar(categoria);
    }

    @Override
    public CategoriaDomain actualizar(Integer id, CategoriaDomain categoria) {
        log.info("Actualizando categoría con ID: {}", id);

        if (!categoriaRepositoryPort.existePorId(id)) {
            throw new EntityNotFoundException("Categoría", id);
        }

        categoria.setIdCategoria(id);
        categoria.validar();
        return categoriaRepositoryPort.guardar(categoria);
    }

    @Override
    public void eliminar(Integer id) {
        log.info("Eliminando categoría con ID: {}", id);

        if (!categoriaRepositoryPort.existePorId(id)) {
            throw new EntityNotFoundException("Categoría", id);
        }

        categoriaRepositoryPort.eliminar(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategoriaDomain> obtenerPorId(Integer id) {
        log.info("Obteniendo categoría con ID: {}", id);
        return categoriaRepositoryPort.buscarPorId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaDomain> obtenerTodas() {
        log.info("Obteniendo todas las categorías");
        return categoriaRepositoryPort.buscarTodas();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaDomain> buscarPorNombre(String nombre) {
        log.info("Buscando categorías por nombre: {}", nombre);
        return categoriaRepositoryPort.buscarPorNombreContiene(nombre);
    }
}