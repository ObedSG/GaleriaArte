package org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.mapper;

import org.ipn.mx.galeriaarte.domain.model.ObraDigitalDomain;
import org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.entity.ObraDigital;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObraDigitalEntityMapper {

    public ObraDigitalDomain toDomain(ObraDigital entity) {
        if (entity == null) return null;

        return ObraDigitalDomain.builder()
                .idObraDigital(entity.getIdObraDigital())
                .titulo(entity.getTitulo())
                .descripcion(entity.getDescripcion())
                .fechaPublicacion(entity.getFechaPublicacion())
                .idAutor(entity.getIdAutor())
                .idArchivoPrincipal(entity.getIdArchivoPrincipal())
                .build();
    }

    public ObraDigital toEntity(ObraDigitalDomain domain) {
        if (domain == null) return null;

        ObraDigital entity = new ObraDigital();
        entity.setIdObraDigital(domain.getIdObraDigital());
        entity.setTitulo(domain.getTitulo());
        entity.setDescripcion(domain.getDescripcion());
        entity.setFechaPublicacion(domain.getFechaPublicacion());
        entity.setIdAutor(domain.getIdAutor());
        entity.setIdArchivoPrincipal(domain.getIdArchivoPrincipal());
        return entity;
    }

    public List<ObraDigitalDomain> toDomainList(List<ObraDigital> entityList) {
        if (entityList == null) return null;
        return entityList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}