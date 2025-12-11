package org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.model.CategoriaDomain;
import org.ipn.mx.galeriaarte.domain.port.out.CategoriaRepositoryPort;
import org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.entity.Categoria;
import org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.mapper.CategoriaEntityMapper;
import org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.repository.CategoriaJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoriaRepositoryAdapter implements CategoriaRepositoryPort {

    private final CategoriaJpaRepository categoriaJpaRepository;
    private final CategoriaEntityMapper categoriaEntityMapper;

    @Override
    public CategoriaDomain guardar(CategoriaDomain categoria) {
        log.debug("Guardando categoría en BD: {}", categoria.getNombreCategoria());
        Categoria entity = categoriaEntityMapper.toEntity(categoria);
        Categoria savedEntity = categoriaJpaRepository.save(entity);
        return categoriaEntityMapper.toDomain(savedEntity);
    }

    @Override
    public void eliminar(Integer id) {
        log.debug("Eliminando categoría con ID: {}", id);
        categoriaJpaRepository.deleteById(id);
    }

    @Override
    public Optional<CategoriaDomain> buscarPorId(Integer id) {
        log.debug("Buscando categoría con ID: {}", id);
        return categoriaJpaRepository.findById(id)
                .map(categoriaEntityMapper::toDomain);
    }

    @Override
    public List<CategoriaDomain> buscarTodas() {
        log.debug("Buscando todas las categorías");
        List<Categoria> entities = categoriaJpaRepository.findAll();
        return categoriaEntityMapper.toDomainList(entities);
    }

    @Override
    public List<CategoriaDomain> buscarPorNombreContiene(String nombre) {
        log.debug("Buscando categorías por nombre: {}", nombre);
        List<Categoria> entities = categoriaJpaRepository.findByNombreCategoriaContainingIgnoreCase(nombre);
        return categoriaEntityMapper.toDomainList(entities);
    }

    @Override
    public boolean existePorId(Integer id) {
        log.debug("Verificando si existe categoría con ID: {}", id);
        return categoriaJpaRepository.existsByIdCategoria(id);
    }
}