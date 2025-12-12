package org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.mapper;

import org.ipn.mx.galeriaarte.domain.model.ArchivoDigitalDomain;
import org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.entity.ArchivoDigital;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArchivoDigitalEntityMapper {

    public ArchivoDigitalDomain toDomain(ArchivoDigital entity) {
        if (entity == null) return null;

        return ArchivoDigitalDomain.builder()
                .idArchivo(entity.getIdArchivo())
                .ruta(entity.getRuta())
                .formato(entity.getFormato())
                .checksum(entity.getChecksum())
                .idObraDigital(entity.getIdObraDigital())
                .build();
    }

    public ArchivoDigital toEntity(ArchivoDigitalDomain domain) {
        if (domain == null) return null;

        ArchivoDigital entity = new ArchivoDigital();
        entity.setIdArchivo(domain.getIdArchivo());
        entity.setRuta(domain.getRuta());
        entity.setFormato(domain.getFormato());
        entity.setChecksum(domain.getChecksum());
        entity.setIdObraDigital(domain.getIdObraDigital());
        return entity;
    }

    public List<ArchivoDigitalDomain> toDomainList(List<ArchivoDigital> entityList) {
        if (entityList == null) return null;
        return entityList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}