package org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.model.ObraDigitalDomain;
import org.ipn.mx.galeriaarte.domain.port.out.ObraDigitalRepositoryPort;
import org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.entity.ObraDigital;
import org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.mapper.ObraDigitalEntityMapper;
import org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.repository.ObraDigitalJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ObraDigitalRepositoryAdapter implements ObraDigitalRepositoryPort {

    private final ObraDigitalJpaRepository obraDigitalJpaRepository;
    private final ObraDigitalEntityMapper obraDigitalEntityMapper;

    @Override
    public ObraDigitalDomain guardar(ObraDigitalDomain obra) {
        log.debug("Guardando obra digital en BD: {}", obra.getTitulo());
        ObraDigital entity = obraDigitalEntityMapper.toEntity(obra);
        ObraDigital savedEntity = obraDigitalJpaRepository.save(entity);
        return obraDigitalEntityMapper.toDomain(savedEntity);
    }

    @Override
    public void eliminar(Integer id) {
        log.debug("Eliminando obra digital con ID: {}", id);
        obraDigitalJpaRepository.deleteById(id);
    }

    @Override
    public Optional<ObraDigitalDomain> buscarPorId(Integer id) {
        log.debug("Buscando obra digital con ID: {}", id);
        return obraDigitalJpaRepository.findById(id)
                .map(obraDigitalEntityMapper::toDomain);
    }

    @Override
    public List<ObraDigitalDomain> buscarTodas() {
        log.debug("Buscando todas las obras digitales");
        List<ObraDigital> entities = obraDigitalJpaRepository.findAll();
        return obraDigitalEntityMapper.toDomainList(entities);
    }

    @Override
    public List<ObraDigitalDomain> buscarPorTituloContiene(String titulo) {
        log.debug("Buscando obras digitales por título: {}", titulo);
        List<ObraDigital> entities = obraDigitalJpaRepository.findByTituloContainingIgnoreCase(titulo);
        return obraDigitalEntityMapper.toDomainList(entities);
    }

    @Override
    public List<ObraDigitalDomain> buscarPorAutor(Integer idAutor) {
        log.debug("Buscando obras digitales del autor con ID: {}", idAutor);
        List<ObraDigital> entities = obraDigitalJpaRepository.findByIdAutor(idAutor);
        return obraDigitalEntityMapper.toDomainList(entities);
    }

    @Override
    public List<ObraDigitalDomain> buscarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        log.debug("Buscando obras digitales entre {} y {}", fechaInicio, fechaFin);
        List<ObraDigital> entities = obraDigitalJpaRepository.findByFechaPublicacionBetween(fechaInicio, fechaFin);
        return obraDigitalEntityMapper.toDomainList(entities);
    }

    @Override
    public List<ObraDigitalDomain> buscarPorArchivoPrincipal(Integer idArchivoPrincipal) {
        log.debug("Buscando obras digitales con archivo principal ID: {}", idArchivoPrincipal);
        List<ObraDigital> entities = obraDigitalJpaRepository.findByIdArchivoPrincipal(idArchivoPrincipal);
        return obraDigitalEntityMapper.toDomainList(entities);
    }

    @Override
    public boolean existePorId(Integer id) {
        log.debug("Verificando si existe obra digital con ID: {}", id);
        return obraDigitalJpaRepository.existsByIdObraDigital(id);
    }
}