package org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.model.ColeccionDomain;
import org.ipn.mx.galeriaarte.domain.port.out.ColeccionRepositoryPort;
import org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.entity.Coleccion;
import org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.mapper.ColeccionEntityMapper;
import org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.repository.ColeccionJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ColeccionRepositoryAdapter implements ColeccionRepositoryPort {

    private final ColeccionJpaRepository coleccionJpaRepository;
    private final ColeccionEntityMapper coleccionEntityMapper;

    @Override
    public ColeccionDomain guardar(ColeccionDomain coleccion) {
        log.debug("Guardando colección en BD: {}", coleccion.getNombreColeccion());
        Coleccion entity = coleccionEntityMapper.toEntity(coleccion);
        Coleccion savedEntity = coleccionJpaRepository.save(entity);
        return coleccionEntityMapper.toDomain(savedEntity);
    }

    @Override
    public void eliminar(Integer id) {
        log.debug("Eliminando colección con ID: {}", id);
        coleccionJpaRepository.deleteById(id);
    }

    @Override
    public Optional<ColeccionDomain> buscarPorId(Integer id) {
        log.debug("Buscando colección con ID: {}", id);
        return coleccionJpaRepository.findById(id)
                .map(coleccionEntityMapper::toDomain);
    }

    @Override
    public List<ColeccionDomain> buscarTodas() {
        log.debug("Buscando todas las colecciones");
        List<Coleccion> entities = coleccionJpaRepository.findAll();
        return coleccionEntityMapper.toDomainList(entities);
    }

    @Override
    public List<ColeccionDomain> buscarPorNombreContiene(String nombre) {
        log.debug("Buscando colecciones por nombre: {}", nombre);
        List<Coleccion> entities = coleccionJpaRepository.findByNombreColeccionContainingIgnoreCase(nombre);
        return coleccionEntityMapper.toDomainList(entities);
    }

    @Override
    public List<ColeccionDomain> buscarActivas(LocalDate fechaActual) {
        log.debug("Buscando colecciones activas para la fecha: {}", fechaActual);
        List<Coleccion> entities = coleccionJpaRepository.findColeccionesActivas(fechaActual);
        return coleccionEntityMapper.toDomainList(entities);
    }

    @Override
    public boolean existePorId(Integer id) {
        log.debug("Verificando si existe colección con ID: {}", id);
        return coleccionJpaRepository.existsByIdColeccion(id);
    }
}