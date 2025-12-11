package org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.mapper;

import org.ipn.mx.galeriaarte.domain.model.AutorDomain;
import org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.entity.Autor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AutorEntityMapper {

    public AutorDomain toDomain(Autor entity) {
        if (entity == null) return null;

        return AutorDomain.builder()
                .idAutor(entity.getIdAutor())
                .nombreCompleto(entity.getNombreCompleto())
                .correoContacto(entity.getCorreoContacto())
                .avatar(entity.getAvatar())
                .build();
    }

    public Autor toEntity(AutorDomain domain) {
        if (domain == null) return null;

        Autor entity = new Autor();
        entity.setIdAutor(domain.getIdAutor());
        entity.setNombreCompleto(domain.getNombreCompleto());
        entity.setCorreoContacto(domain.getCorreoContacto());
        entity.setAvatar(domain.getAvatar());
        return entity;
    }

    public List<AutorDomain> toDomainList(List<Autor> entityList) {
        if (entityList == null) return null;
        return entityList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}