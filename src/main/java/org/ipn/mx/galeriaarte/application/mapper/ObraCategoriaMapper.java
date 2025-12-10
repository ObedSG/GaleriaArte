package org.ipn.mx.galeriaarte.application.mapper;

import org.ipn.mx.galeriaarte.application.dto.ObraCategoriaDTO;
import org.ipn.mx.galeriaarte.domain.model.ObraCategoriaDomain;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObraCategoriaMapper {

    public ObraCategoriaDomain toDomain(ObraCategoriaDTO dto) {
        if (dto == null) return null;
        return ObraCategoriaDomain.builder()
                .idObraCategoria(dto.getIdObraCategoria())
                .idObraDigital(dto.getIdObraDigital())
                .idCategoria(dto.getIdCategoria())
                .build();
    }

    public ObraCategoriaDTO toDTO(ObraCategoriaDomain domain) {
        if (domain == null) return null;
        return ObraCategoriaDTO.builder()
                .idObraCategoria(domain.getIdObraCategoria())
                .idObraDigital(domain.getIdObraDigital())
                .idCategoria(domain.getIdCategoria())
                .build();
    }

    public List<ObraCategoriaDTO> toDTOList(List<ObraCategoriaDomain> domainList) {
        if (domainList == null) return null;
        return domainList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}