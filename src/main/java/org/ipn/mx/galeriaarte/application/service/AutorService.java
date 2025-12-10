package org.ipn.mx.galeriaarte.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.exception.EntityNotFoundException;
import org.ipn.mx.galeriaarte.domain.model.AutorDomain;
import org.ipn.mx.galeriaarte.domain.port.in.AutorUseCase;
import org.ipn.mx.galeriaarte.domain.port.out.AutorRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AutorService implements AutorUseCase {

    private final AutorRepositoryPort autorRepositoryPort;

    @Override
    public AutorDomain crear(AutorDomain autor) {
        log.info("Creando nuevo autor: {}", autor.getNombreCompleto());
        autor.validar();
        return autorRepositoryPort.guardar(autor);
    }

    @Override
    public AutorDomain actualizar(Integer id, AutorDomain autor) {
        log.info("Actualizando autor con ID: {}", id);

        if (!autorRepositoryPort.existePorId(id)) {
            throw new EntityNotFoundException("Autor", id);
        }

        autor.setIdAutor(id);
        autor.validar();
        return autorRepositoryPort.guardar(autor);
    }

    @Override
    public void eliminar(Integer id) {
        log.info("Eliminando autor con ID: {}", id);

        if (!autorRepositoryPort.existePorId(id)) {
            throw new EntityNotFoundException("Autor", id);
        }

        autorRepositoryPort.eliminar(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AutorDomain> obtenerPorId(Integer id) {
        log.info("Obteniendo autor con ID: {}", id);
        return autorRepositoryPort.buscarPorId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AutorDomain> obtenerTodos() {
        log.info("Obteniendo todos los autores");
        return autorRepositoryPort.buscarTodos();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AutorDomain> buscarPorNombre(String nombre) {
        log.info("Buscando autores por nombre: {}", nombre);
        return autorRepositoryPort.buscarPorNombreContiene(nombre);
    }
}