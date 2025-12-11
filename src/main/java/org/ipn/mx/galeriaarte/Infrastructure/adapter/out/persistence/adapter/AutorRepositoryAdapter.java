package org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.model.AutorDomain;
import org.ipn.mx.galeriaarte.domain.port.out.AutorRepositoryPort;
import org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.entity.Autor;
import org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.mapper.AutorEntityMapper;
import org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.repository.AutorJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AutorRepositoryAdapter implements AutorRepositoryPort {

    private final AutorJpaRepository autorJpaRepository;
    private final AutorEntityMapper autorEntityMapper;

    @Override
    public AutorDomain guardar(AutorDomain autor) {
        log.debug("Guardando autor en BD: {}", autor.getNombreCompleto());
        Autor entity = autorEntityMapper.toEntity(autor);
        Autor savedEntity = autorJpaRepository.save(entity);
        return autorEntityMapper.toDomain(savedEntity);
    }

    @Override
    public void eliminar(Integer id) {
        log.debug("Eliminando autor con ID: {}", id);
        autorJpaRepository.deleteById(id);
    }

    @Override
    public Optional<AutorDomain> buscarPorId(Integer id) {
        log.debug("Buscando autor con ID: {}", id);
        return autorJpaRepository.findById(id)
                .map(autorEntityMapper::toDomain);
    }

    @Override
    public List<AutorDomain> buscarTodos() {
        log.debug("Buscando todos los autores");
        List<Autor> entities = autorJpaRepository.findAll();
        return autorEntityMapper.toDomainList(entities);
    }

    @Override
    public List<AutorDomain> buscarPorNombreContiene(String nombre) {
        log.debug("Buscando autores por nombre: {}", nombre);
        List<Autor> entities = autorJpaRepository.findByNombreCompletoContainingIgnoreCase(nombre);
        return autorEntityMapper.toDomainList(entities);
    }

    @Override
    public boolean existePorId(Integer id) {
        log.debug("Verificando si existe autor con ID: {}", id);
        return autorJpaRepository.existsByIdAutor(id);
    }
}