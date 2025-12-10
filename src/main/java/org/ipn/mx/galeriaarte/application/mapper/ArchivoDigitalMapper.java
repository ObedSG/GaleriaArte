package org.ipn.mx.galeriaarte.application.mapper;

import org.ipn.mx.galeriaarte.application.dto.ArchivoDigitalDTO;
import org.ipn.mx.galeriaarte.domain.model.ArchivoDigitalDomain;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArchivoDigitalMapper {

    public ArchivoDigitalDomain toDomain(ArchivoDigitalDTO dto) {
        if (dto == null) return null;
        return ArchivoDigitalDomain.builder()
                .idArchivo(dto.getIdArchivo())
                .ruta(dto.getRuta())
                .formato(dto.getFormato())
                .checksum(dto.getChecksum())
                .idObraDigital(dto.getIdObraDigital())
                .build();
    }

    public ArchivoDigitalDTO toDTO(ArchivoDigitalDomain domain) {
        if (domain == null) return null;
        return ArchivoDigitalDTO.builder()
                .idArchivo(domain.getIdArchivo())
                .ruta(domain.getRuta())
                .formato(domain.getFormato())
                .checksum(domain.getChecksum())
                .idObraDigital(domain.getIdObraDigital())
                .build();
    }

    public List<ArchivoDigitalDTO> toDTOList(List<ArchivoDigitalDomain> domainList) {
        if (domainList == null) return null;
        return domainList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}