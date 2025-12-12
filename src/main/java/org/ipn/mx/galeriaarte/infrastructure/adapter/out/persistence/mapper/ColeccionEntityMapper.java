package org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.mapper;

import org.ipn.mx.galeriaarte.domain.model.ColeccionDomain;
import org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.entity.Coleccion;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ColeccionEntityMapper {

    public ColeccionDomain toDomain(Coleccion entity) {
        if (entity == null) return null;

        return ColeccionDomain.builder()
                .idColeccion(entity.getIdColeccion())
                .nombreColeccion(entity.getNombreColeccion())
                .descripcionColeccion(entity.getDescripcionColeccion())
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .build();
    }

    public Coleccion toEntity(ColeccionDomain domain) {
        if (domain == null) return null;

        Coleccion entity = new Coleccion();
        entity.setIdColeccion(domain.getIdColeccion());
        entity.setNombreColeccion(domain.getNombreColeccion());
        entity.setDescripcionColeccion(domain.getDescripcionColeccion());
        entity.setFechaInicio(domain.getFechaInicio());
        entity.setFechaFin(domain.getFechaFin());
        return entity;
    }

    public List<ColeccionDomain> toDomainList(List<Coleccion> entityList) {
        if (entityList == null) return null;
        return entityList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}