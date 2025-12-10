package org.ipn.mx.galeriaarte.application.mapper;

import org.ipn.mx.galeriaarte.application.dto.CategoriaDTO;
import org.ipn.mx.galeriaarte.domain.model.CategoriaDomain;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoriaMapper {

    public CategoriaDomain toDomain(CategoriaDTO dto) {
        if (dto == null) return null;
        return CategoriaDomain.builder()
                .idCategoria(dto.getIdCategoria())
                .nombreCategoria(dto.getNombreCategoria())
                .descripcionCategoria(dto.getDescripcionCategoria())
                .build();
    }

    public CategoriaDTO toDTO(CategoriaDomain domain) {
        if (domain == null) return null;
        return CategoriaDTO.builder()
                .idCategoria(domain.getIdCategoria())
                .nombreCategoria(domain.getNombreCategoria())
                .descripcionCategoria(domain.getDescripcionCategoria())
                .build();
    }

    public List<CategoriaDTO> toDTOList(List<CategoriaDomain> domainList) {
        if (domainList == null) return null;
        return domainList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}