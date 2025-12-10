package org.ipn.mx.galeriaarte.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.exception.BusinessException;
import org.ipn.mx.galeriaarte.domain.exception.EntityNotFoundException;
import org.ipn.mx.galeriaarte.domain.model.ColeccionObraDomain;
import org.ipn.mx.galeriaarte.domain.port.in.ColeccionObraUseCase;
import org.ipn.mx.galeriaarte.domain.port.out.ColeccionObraRepositoryPort;
import org.ipn.mx.galeriaarte.domain.port.out.ColeccionRepositoryPort;
import org.ipn.mx.galeriaarte.domain.port.out.ObraDigitalRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ColeccionObraService implements ColeccionObraUseCase {

    private final ColeccionObraRepositoryPort coleccionObraRepositoryPort;
    private final ColeccionRepositoryPort coleccionRepositoryPort;
    private final ObraDigitalRepositoryPort obraDigitalRepositoryPort;

    @Override
    public ColeccionObraDomain agregar(ColeccionObraDomain relacion) {
        log.info("Agregando obra {} a colección {}", relacion.getIdObraDigital(), relacion.getIdColeccion());
        relacion.validar();

        // Validar que la colección existe
        if (!coleccionRepositoryPort.existePorId(relacion.getIdColeccion())) {
            throw new BusinessException("La colección con ID " + relacion.getIdColeccion() + " no existe");
        }

        // Validar que la obra existe
        if (!obraDigitalRepositoryPort.existePorId(relacion.getIdObraDigital())) {
            throw new BusinessException("La obra digital con ID " + relacion.getIdObraDigital() + " no existe");
        }

        // Validar que no existe la relación
        if (coleccionObraRepositoryPort.existeRelacion(relacion.getIdColeccion(), relacion.getIdObraDigital())) {
            throw new BusinessException("La obra ya pertenece a esta colección");
        }

        return coleccionObraRepositoryPort.guardar(relacion);
    }

    @Override
    public void eliminar(Integer id) {
        log.info("Eliminando relación colección-obra con ID: {}", id);

        if (!coleccionObraRepositoryPort.buscarPorId(id).isPresent()) {
            throw new EntityNotFoundException("Relación Colección-Obra", id);
        }

        coleccionObraRepositoryPort.eliminar(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ColeccionObraDomain> obtenerPorId(Integer id) {
        log.info("Obteniendo relación colección-obra con ID: {}", id);
        return coleccionObraRepositoryPort.buscarPorId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ColeccionObraDomain> obtenerPorColeccion(Integer idColeccion) {
        log.info("Obteniendo obras de la colección con ID: {}", idColeccion);
        return coleccionObraRepositoryPort.buscarPorColeccion(idColeccion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ColeccionObraDomain> obtenerPorObra(Integer idObraDigital) {
        log.info("Obteniendo colecciones de la obra con ID: {}", idObraDigital);
        return coleccionObraRepositoryPort.buscarPorObra(idObraDigital);
    }

    @Override
    public void eliminarPorColeccionYObra(Integer idColeccion, Integer idObraDigital) {
        log.info("Eliminando obra {} de colección {}", idObraDigital, idColeccion);
        coleccionObraRepositoryPort.eliminarPorColeccionYObra(idColeccion, idObraDigital);
    }
}