package org.ipn.mx.galeriaarte.application.mapper;

import org.ipn.mx.galeriaarte.application.dto.ColeccionDTO;
import org.ipn.mx.galeriaarte.domain.model.ColeccionDomain;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ColeccionMapper {

    public ColeccionDomain toDomain(ColeccionDTO dto) {
        if (dto == null) return null;
        return ColeccionDomain.builder()
                .idColeccion(dto.getIdColeccion())
                .nombreColeccion(dto.getNombreColeccion())
                .descripcionColeccion(dto.getDescripcionColeccion())
                .fechaInicio(dto.getFechaInicio())
                .fechaFin(dto.getFechaFin())
                .build();
    }

    public ColeccionDTO toDTO(ColeccionDomain domain) {
        if (domain == null) return null;
        return ColeccionDTO.builder()
                .idColeccion(domain.getIdColeccion())
                .nombreColeccion(domain.getNombreColeccion())
                .descripcionColeccion(domain.getDescripcionColeccion())
                .fechaInicio(domain.getFechaInicio())
                .fechaFin(domain.getFechaFin())
                .build();
    }

    public List<ColeccionDTO> toDTOList(List<ColeccionDomain> domainList) {
        if (domainList == null) return null;
        return domainList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}