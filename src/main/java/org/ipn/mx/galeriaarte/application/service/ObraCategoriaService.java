package org.ipn.mx.galeriaarte.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.exception.BusinessException;
import org.ipn.mx.galeriaarte.domain.exception.EntityNotFoundException;
import org.ipn.mx.galeriaarte.domain.model.ObraCategoriaDomain;
import org.ipn.mx.galeriaarte.domain.port.in.ObraCategoriaUseCase;
import org.ipn.mx.galeriaarte.domain.port.out.CategoriaRepositoryPort;
import org.ipn.mx.galeriaarte.domain.port.out.ObraCategoriaRepositoryPort;
import org.ipn.mx.galeriaarte.domain.port.out.ObraDigitalRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ObraCategoriaService implements ObraCategoriaUseCase {

    private final ObraCategoriaRepositoryPort obraCategoriaRepositoryPort;
    private final ObraDigitalRepositoryPort obraDigitalRepositoryPort;
    private final CategoriaRepositoryPort categoriaRepositoryPort;

    @Override
    public ObraCategoriaDomain agregar(ObraCategoriaDomain relacion) {
        log.info("Agregando categoría {} a obra {}", relacion.getIdCategoria(), relacion.getIdObraDigital());
        relacion.validar();

        // Validar que la obra existe
        if (!obraDigitalRepositoryPort.existePorId(relacion.getIdObraDigital())) {
            throw new BusinessException("La obra digital con ID " + relacion.getIdObraDigital() + " no existe");
        }

        // Validar que la categoría existe
        if (!categoriaRepositoryPort.existePorId(relacion.getIdCategoria())) {
            throw new BusinessException("La categoría con ID " + relacion.getIdCategoria() + " no existe");
        }

        // Validar que no existe la relación
        if (obraCategoriaRepositoryPort.existeRelacion(relacion.getIdObraDigital(), relacion.getIdCategoria())) {
            throw new BusinessException("La obra ya tiene asignada esta categoría");
        }

        return obraCategoriaRepositoryPort.guardar(relacion);
    }

    @Override
    public void eliminar(Integer id) {
        log.info("Eliminando relación obra-categoría con ID: {}", id);

        if (!obraCategoriaRepositoryPort.buscarPorId(id).isPresent()) {
            throw new EntityNotFoundException("Relación Obra-Categoría", id);
        }

        obraCategoriaRepositoryPort.eliminar(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ObraCategoriaDomain> obtenerPorId(Integer id) {
        log.info("Obteniendo relación obra-categoría con ID: {}", id);
        return obraCategoriaRepositoryPort.buscarPorId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ObraCategoriaDomain> obtenerPorCategoria(Integer idCategoria) {
        log.info("Obteniendo obras de la categoría con ID: {}", idCategoria);
        return obraCategoriaRepositoryPort.buscarPorCategoria(idCategoria);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ObraCategoriaDomain> obtenerPorObra(Integer idObraDigital) {
        log.info("Obteniendo categorías de la obra con ID: {}", idObraDigital);
        return obraCategoriaRepositoryPort.buscarPorObra(idObraDigital);
    }

    @Override
    public void eliminarPorObraYCategoria(Integer idObraDigital, Integer idCategoria) {
        log.info("Eliminando categoría {} de obra {}", idCategoria, idObraDigital);
        obraCategoriaRepositoryPort.eliminarPorObraYCategoria(idObraDigital, idCategoria);
    }
}