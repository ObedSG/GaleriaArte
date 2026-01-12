package org.ipn.mx.galeriaarte.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.exception.BusinessException;
import org.ipn.mx.galeriaarte.domain.exception.EntityNotFoundException;
import org.ipn.mx.galeriaarte.domain.model.ArchivoDigitalDomain;
import org.ipn.mx.galeriaarte.domain.port.in.ArchivoDigitalUseCase;
import org.ipn.mx.galeriaarte.domain.port.out.ArchivoDigitalRepositoryPort;
import org.ipn.mx.galeriaarte.domain.port.out.ObraDigitalRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ArchivoDigitalService implements ArchivoDigitalUseCase {

    private final ArchivoDigitalRepositoryPort archivoDigitalRepositoryPort;
    private final ObraDigitalRepositoryPort obraDigitalRepositoryPort;

    @Override
    public ArchivoDigitalDomain crear(ArchivoDigitalDomain archivo) {
        log.info("Creando nuevo archivo digital para obra: {}", archivo.getIdObraDigital());
        archivo.validar();

        // Validar que la obra digital existe (si se proporciona)
        if (archivo.getIdObraDigital() != null) {
            if (!obraDigitalRepositoryPort.existePorId(archivo.getIdObraDigital())) {
                throw new BusinessException("La obra digital con ID " + archivo.getIdObraDigital() + " no existe");
            }
        }

        return archivoDigitalRepositoryPort.guardar(archivo);
    }

    @Override
    public ArchivoDigitalDomain actualizar(Integer id, ArchivoDigitalDomain archivo) {
        log.info("Actualizando archivo digital con ID: {}", id);

        if (!archivoDigitalRepositoryPort.existePorId(id)) {
            throw new EntityNotFoundException("Archivo Digital", id);
        }

        archivo.setIdArchivo(id);
        archivo.validar();

        // Validar que la obra digital existe
        if (archivo.getIdObraDigital() != null && !obraDigitalRepositoryPort.existePorId(archivo.getIdObraDigital())) {
            throw new BusinessException("La obra digital con ID " + archivo.getIdObraDigital() + " no existe");
        }

        return archivoDigitalRepositoryPort.guardar(archivo);
    }

    @Override
    public void eliminar(Integer id) {
        log.info("Eliminando archivo digital con ID: {}", id);

        if (!archivoDigitalRepositoryPort.existePorId(id)) {
            throw new EntityNotFoundException("Archivo Digital", id);
        }

        archivoDigitalRepositoryPort.eliminar(id);
        log.info("Archivo digital eliminado exitosamente: ID {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArchivoDigitalDomain> obtenerPorId(Integer id) {
        log.info("Obteniendo archivo digital con ID: {}", id);
        return archivoDigitalRepositoryPort.buscarPorId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArchivoDigitalDomain> obtenerTodos() {
        log.info("Obteniendo todos los archivos digitales");
        return archivoDigitalRepositoryPort.buscarTodos();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArchivoDigitalDomain> obtenerPorObraDigital(Integer idObraDigital) {
        log.info("Obteniendo archivos de la obra digital con ID: {}", idObraDigital);
        return archivoDigitalRepositoryPort.buscarPorObraDigital(idObraDigital);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArchivoDigitalDomain> obtenerPorFormato(String formato) {
        log.info("Obteniendo archivos por formato: {}", formato);
        return archivoDigitalRepositoryPort.buscarPorFormato(formato);
    }
}