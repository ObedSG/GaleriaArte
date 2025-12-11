package org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.mapper;

import org.ipn.mx.galeriaarte.domain.model.ColeccionObraDomain;
import org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.entity.ColeccionObra;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ColeccionObraEntityMapper {

    public ColeccionObraDomain toDomain(ColeccionObra entity) {
        if (entity == null) return null;

        return ColeccionObraDomain.builder()
                .idColeccionObra(entity.getIdColeccionObra())
                .idObraDigital(entity.getIdObraDigital())
                .idColeccion(entity.getIdColeccion())
                .build();
    }

    public ColeccionObra toEntity(ColeccionObraDomain domain) {
        if (domain == null) return null;

        ColeccionObra entity = new ColeccionObra();
        entity.setIdColeccionObra(domain.getIdColeccionObra());
        entity.setIdObraDigital(domain.getIdObraDigital());
        entity.setIdColeccion(domain.getIdColeccion());
        return entity;
    }

    public List<ColeccionObraDomain> toDomainList(List<ColeccionObra> entityList) {
        if (entityList == null) return null;
        return entityList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}