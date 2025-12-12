package org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.model.ArchivoDigitalDomain;
import org.ipn.mx.galeriaarte.domain.port.out.ArchivoDigitalRepositoryPort;
import org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.entity.ArchivoDigital;
import org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.mapper.ArchivoDigitalEntityMapper;
import org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.repository.ArchivoDigitalJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ArchivoDigitalRepositoryAdapter implements ArchivoDigitalRepositoryPort {

    private final ArchivoDigitalJpaRepository archivoDigitalJpaRepository;
    private final ArchivoDigitalEntityMapper archivoDigitalEntityMapper;

    @Override
    public ArchivoDigitalDomain guardar(ArchivoDigitalDomain archivo) {
        log.debug("Guardando archivo digital en BD: {}", archivo.getRuta());
        ArchivoDigital entity = archivoDigitalEntityMapper.toEntity(archivo);
        ArchivoDigital savedEntity = archivoDigitalJpaRepository.save(entity);
        return archivoDigitalEntityMapper.toDomain(savedEntity);
    }

    @Override
    public void eliminar(Integer id) {
        log.debug("Eliminando archivo digital con ID: {}", id);
        archivoDigitalJpaRepository.deleteById(id);
    }

    @Override
    public Optional<ArchivoDigitalDomain> buscarPorId(Integer id) {
        log.debug("Buscando archivo digital con ID: {}", id);
        return archivoDigitalJpaRepository.findById(id)
                .map(archivoDigitalEntityMapper::toDomain);
    }

    @Override
    public List<ArchivoDigitalDomain> buscarPorObraDigital(Integer idObraDigital) {
        log.debug("Buscando archivos de la obra digital con ID: {}", idObraDigital);
        List<ArchivoDigital> entities = archivoDigitalJpaRepository.findByIdObraDigital(idObraDigital);
        return archivoDigitalEntityMapper.toDomainList(entities);
    }

    @Override
    public List<ArchivoDigitalDomain> buscarPorFormato(String formato) {
        log.debug("Buscando archivos por formato: {}", formato);
        List<ArchivoDigital> entities = archivoDigitalJpaRepository.findByFormatoIgnoreCase(formato);
        return archivoDigitalEntityMapper.toDomainList(entities);
    }

    @Override
    public boolean existePorId(Integer id) {
        log.debug("Verificando si existe archivo digital con ID: {}", id);
        return archivoDigitalJpaRepository.existsByIdArchivo(id);
    }
}