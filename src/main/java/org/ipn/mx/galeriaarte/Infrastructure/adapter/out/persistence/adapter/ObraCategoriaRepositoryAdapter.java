package org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.model.ObraCategoriaDomain;
import org.ipn.mx.galeriaarte.domain.port.out.ObraCategoriaRepositoryPort;
import org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.entity.ObraCategoria;
import org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.mapper.ObraCategoriaEntityMapper;
import org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.repository.ObraCategoriaJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ObraCategoriaRepositoryAdapter implements ObraCategoriaRepositoryPort {

    private final ObraCategoriaJpaRepository obraCategoriaJpaRepository;
    private final ObraCategoriaEntityMapper obraCategoriaEntityMapper;

    @Override
    public ObraCategoriaDomain guardar(ObraCategoriaDomain relacion) {
        log.debug("Guardando relación obra-categoría en BD");
        ObraCategoria entity = obraCategoriaEntityMapper.toEntity(relacion);
        ObraCategoria savedEntity = obraCategoriaJpaRepository.save(entity);
        return obraCategoriaEntityMapper.toDomain(savedEntity);
    }

    @Override
    public void eliminar(Integer id) {
        log.debug("Eliminando relación obra-categoría con ID: {}", id);
        obraCategoriaJpaRepository.deleteById(id);
    }

    @Override
    public Optional<ObraCategoriaDomain> buscarPorId(Integer id) {
        log.debug("Buscando relación obra-categoría con ID: {}", id);
        return obraCategoriaJpaRepository.findById(id)
                .map(obraCategoriaEntityMapper::toDomain);
    }

    @Override
    public List<ObraCategoriaDomain> buscarPorCategoria(Integer idCategoria) {
        log.debug("Buscando obras de la categoría con ID: {}", idCategoria);
        List<ObraCategoria> entities = obraCategoriaJpaRepository.findByIdCategoria(idCategoria);
        return obraCategoriaEntityMapper.toDomainList(entities);
    }

    @Override
    public List<ObraCategoriaDomain> buscarPorObra(Integer idObraDigital) {
        log.debug("Buscando categorías de la obra con ID: {}", idObraDigital);
        List<ObraCategoria> entities = obraCategoriaJpaRepository.findByIdObraDigital(idObraDigital);
        return obraCategoriaEntityMapper.toDomainList(entities);
    }

    @Override
    @Transactional
    public void eliminarPorObraYCategoria(Integer idObraDigital, Integer idCategoria) {
        log.debug("Eliminando categoría {} de obra {}", idCategoria, idObraDigital);
        obraCategoriaJpaRepository.deleteByIdObraDigitalAndIdCategoria(idObraDigital, idCategoria);
    }

    @Override
    public boolean existeRelacion(Integer idObraDigital, Integer idCategoria) {
        log.debug("Verificando si existe relación entre obra {} y categoría {}", idObraDigital, idCategoria);
        return obraCategoriaJpaRepository.existsByIdObraDigitalAndIdCategoria(idObraDigital, idCategoria);
    }
}