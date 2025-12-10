package org.ipn.mx.galeriaarte.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.exception.EntityNotFoundException;
import org.ipn.mx.galeriaarte.domain.model.ColeccionDomain;
import org.ipn.mx.galeriaarte.domain.port.in.ColeccionUseCase;
import org.ipn.mx.galeriaarte.domain.port.out.ColeccionRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ColeccionService implements ColeccionUseCase {

    private final ColeccionRepositoryPort coleccionRepositoryPort;

    @Override
    public ColeccionDomain crear(ColeccionDomain coleccion) {
        log.info("Creando nueva colección: {}", coleccion.getNombreColeccion());
        coleccion.validar();
        return coleccionRepositoryPort.guardar(coleccion);
    }

    @Override
    public ColeccionDomain actualizar(Integer id, ColeccionDomain coleccion) {
        log.info("Actualizando colección con ID: {}", id);

        if (!coleccionRepositoryPort.existePorId(id)) {
            throw new EntityNotFoundException("Colección", id);
        }

        coleccion.setIdColeccion(id);
        coleccion.validar();
        return coleccionRepositoryPort.guardar(coleccion);
    }

    @Override
    public void eliminar(Integer id) {
        log.info("Eliminando colección con ID: {}", id);

        if (!coleccionRepositoryPort.existePorId(id)) {
            throw new EntityNotFoundException("Colección", id);
        }

        coleccionRepositoryPort.eliminar(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ColeccionDomain> obtenerPorId(Integer id) {
        log.info("Obteniendo colección con ID: {}", id);
        return coleccionRepositoryPort.buscarPorId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ColeccionDomain> obtenerTodas() {
        log.info("Obteniendo todas las colecciones");
        return coleccionRepositoryPort.buscarTodas();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ColeccionDomain> buscarPorNombre(String nombre) {
        log.info("Buscando colecciones por nombre: {}", nombre);
        return coleccionRepositoryPort.buscarPorNombreContiene(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ColeccionDomain> obtenerColeccionesActivas() {
        log.info("Obteniendo colecciones activas");
        LocalDate fechaActual = LocalDate.now();
        return coleccionRepositoryPort.buscarActivas(fechaActual);
    }
}