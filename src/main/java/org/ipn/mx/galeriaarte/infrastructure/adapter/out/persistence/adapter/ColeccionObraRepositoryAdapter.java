package org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.model.ColeccionObraDomain;
import org.ipn.mx.galeriaarte.domain.port.out.ColeccionObraRepositoryPort;
import org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.entity.ColeccionObra;
import org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.mapper.ColeccionObraEntityMapper;
import org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.repository.ColeccionObraJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ColeccionObraRepositoryAdapter implements ColeccionObraRepositoryPort {

    private final ColeccionObraJpaRepository coleccionObraJpaRepository;
    private final ColeccionObraEntityMapper coleccionObraEntityMapper;

    @Override
    public ColeccionObraDomain guardar(ColeccionObraDomain relacion) {
        log.debug("Guardando relación colección-obra en BD");
        ColeccionObra entity = coleccionObraEntityMapper.toEntity(relacion);
        ColeccionObra savedEntity = coleccionObraJpaRepository.save(entity);
        return coleccionObraEntityMapper.toDomain(savedEntity);
    }

    @Override
    public void eliminar(Integer id) {
        log.debug("Eliminando relación colección-obra con ID: {}", id);
        coleccionObraJpaRepository.deleteById(id);
    }

    @Override
    public Optional<ColeccionObraDomain> buscarPorId(Integer id) {
        log.debug("Buscando relación colección-obra con ID: {}", id);
        return coleccionObraJpaRepository.findById(id)
                .map(coleccionObraEntityMapper::toDomain);
    }

    @Override
    public List<ColeccionObraDomain> buscarPorColeccion(Integer idColeccion) {
        log.debug("Buscando obras de la colección con ID: {}", idColeccion);
        List<ColeccionObra> entities = coleccionObraJpaRepository.findByIdColeccion(idColeccion);
        return coleccionObraEntityMapper.toDomainList(entities);
    }

    @Override
    public List<ColeccionObraDomain> buscarPorObra(Integer idObraDigital) {
        log.debug("Buscando colecciones de la obra con ID: {}", idObraDigital);
        List<ColeccionObra> entities = coleccionObraJpaRepository.findByIdObraDigital(idObraDigital);
        return coleccionObraEntityMapper.toDomainList(entities);
    }

    @Override
    @Transactional
    public void eliminarPorColeccionYObra(Integer idColeccion, Integer idObraDigital) {
        log.debug("Eliminando obra {} de colección {}", idObraDigital, idColeccion);
        coleccionObraJpaRepository.deleteByIdColeccionAndIdObraDigital(idColeccion, idObraDigital);
    }

    @Override
    public boolean existeRelacion(Integer idColeccion, Integer idObraDigital) {
        log.debug("Verificando si existe relación entre colección {} y obra {}", idColeccion, idObraDigital);
        return coleccionObraJpaRepository.existsByIdColeccionAndIdObraDigital(idColeccion, idObraDigital);
    }
}