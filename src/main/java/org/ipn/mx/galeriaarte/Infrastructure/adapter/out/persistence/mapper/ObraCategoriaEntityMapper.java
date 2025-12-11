package org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.mapper;

import org.ipn.mx.galeriaarte.domain.model.ObraCategoriaDomain;
import org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.entity.ObraCategoria;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObraCategoriaEntityMapper {

    public ObraCategoriaDomain toDomain(ObraCategoria entity) {
        if (entity == null) return null;

        return ObraCategoriaDomain.builder()
                .idObraCategoria(entity.getIdObraCategoria())
                .idObraDigital(entity.getIdObraDigital())
                .idCategoria(entity.getIdCategoria())
                .build();
    }

    public ObraCategoria toEntity(ObraCategoriaDomain domain) {
        if (domain == null) return null;

        ObraCategoria entity = new ObraCategoria();
        entity.setIdObraCategoria(domain.getIdObraCategoria());
        entity.setIdObraDigital(domain.getIdObraDigital());
        entity.setIdCategoria(domain.getIdCategoria());
        return entity;
    }

    public List<ObraCategoriaDomain> toDomainList(List<ObraCategoria> entityList) {
        if (entityList == null) return null;
        return entityList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}