package org.ipn.mx.galeriaarte.application.mapper;

import org.ipn.mx.galeriaarte.application.dto.ColeccionObraDTO;
import org.ipn.mx.galeriaarte.domain.model.ColeccionObraDomain;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ColeccionObraMapper {

    public ColeccionObraDomain toDomain(ColeccionObraDTO dto) {
        if (dto == null) return null;
        return ColeccionObraDomain.builder()
                .idColeccionObra(dto.getIdColeccionObra())
                .idObraDigital(dto.getIdObraDigital())
                .idColeccion(dto.getIdColeccion())
                .build();
    }

    public ColeccionObraDTO toDTO(ColeccionObraDomain domain) {
        if (domain == null) return null;
        return ColeccionObraDTO.builder()
                .idColeccionObra(domain.getIdColeccionObra())
                .idObraDigital(domain.getIdObraDigital())
                .idColeccion(domain.getIdColeccion())
                .build();
    }

    public List<ColeccionObraDTO> toDTOList(List<ColeccionObraDomain> domainList) {
        if (domainList == null) return null;
        return domainList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}