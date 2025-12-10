package org.ipn.mx.galeriaarte.application.mapper;

import org.ipn.mx.galeriaarte.application.dto.ObraDigitalDTO;
import org.ipn.mx.galeriaarte.domain.model.ObraDigitalDomain;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObraDigitalMapper {

    public ObraDigitalDomain toDomain(ObraDigitalDTO dto) {
        if (dto == null) return null;
        return ObraDigitalDomain.builder()
                .idObraDigital(dto.getIdObraDigital())
                .titulo(dto.getTitulo())
                .descripcion(dto.getDescripcion())
                .fechaPublicacion(dto.getFechaPublicacion())
                .idAutor(dto.getIdAutor())
                .idArchivoPrincipal(dto.getIdArchivoPrincipal())
                .build();
    }

    public ObraDigitalDTO toDTO(ObraDigitalDomain domain) {
        if (domain == null) return null;
        return ObraDigitalDTO.builder()
                .idObraDigital(domain.getIdObraDigital())
                .titulo(domain.getTitulo())
                .descripcion(domain.getDescripcion())
                .fechaPublicacion(domain.getFechaPublicacion())
                .idAutor(domain.getIdAutor())
                .idArchivoPrincipal(domain.getIdArchivoPrincipal())
                .build();
    }

    public List<ObraDigitalDTO> toDTOList(List<ObraDigitalDomain> domainList) {
        if (domainList == null) return null;
        return domainList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}