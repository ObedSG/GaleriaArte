package org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.mapper;

import org.ipn.mx.galeriaarte.domain.model.CategoriaDomain;
import org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.entity.Categoria;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoriaEntityMapper {

    public CategoriaDomain toDomain(Categoria entity) {
        if (entity == null) return null;

        return CategoriaDomain.builder()
                .idCategoria(entity.getIdCategoria())
                .nombreCategoria(entity.getNombreCategoria())
                .descripcionCategoria(entity.getDescripcionCategoria())
                .build();
    }

    public Categoria toEntity(CategoriaDomain domain) {
        if (domain == null) return null;

        Categoria entity = new Categoria();
        entity.setIdCategoria(domain.getIdCategoria());
        entity.setNombreCategoria(domain.getNombreCategoria());
        entity.setDescripcionCategoria(domain.getDescripcionCategoria());
        return entity;
    }

    public List<CategoriaDomain> toDomainList(List<Categoria> entityList) {
        if (entityList == null) return null;
        return entityList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}