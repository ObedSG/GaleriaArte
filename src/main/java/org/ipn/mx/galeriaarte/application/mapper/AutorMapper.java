package org.ipn.mx.galeriaarte.application.mapper;

import org.ipn.mx.galeriaarte.application.dto.AutorDTO;
import org.ipn.mx.galeriaarte.domain.model.AutorDomain;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AutorMapper {

    public AutorDomain toDomain(AutorDTO dto) {
        if (dto == null) return null;
        return AutorDomain.builder()
                .idAutor(dto.getIdAutor())
                .nombreCompleto(dto.getNombreCompleto())
                .correoContacto(dto.getCorreoContacto())
                .avatar(dto.getAvatar())
                .build();
    }

    public AutorDTO toDTO(AutorDomain domain) {
        if (domain == null) return null;
        return AutorDTO.builder()
                .idAutor(domain.getIdAutor())
                .nombreCompleto(domain.getNombreCompleto())
                .correoContacto(domain.getCorreoContacto())
                .avatar(domain.getAvatar())
                .build();
    }

    public List<AutorDTO> toDTOList(List<AutorDomain> domainList) {
        if (domainList == null) return null;
        return domainList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}