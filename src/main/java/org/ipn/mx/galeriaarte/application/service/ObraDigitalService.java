package org.ipn.mx.galeriaarte.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipn.mx.galeriaarte.domain.exception.BusinessException;
import org.ipn.mx.galeriaarte.domain.exception.EntityNotFoundException;
import org.ipn.mx.galeriaarte.domain.model.ArchivoDigitalDomain;
import org.ipn.mx.galeriaarte.domain.model.ObraDigitalDomain;
import org.ipn.mx.galeriaarte.domain.port.in.ObraDigitalUseCase;
import org.ipn.mx.galeriaarte.domain.port.out.ArchivoDigitalRepositoryPort;
import org.ipn.mx.galeriaarte.domain.port.out.AutorRepositoryPort;
import org.ipn.mx.galeriaarte.domain.port.out.ObraDigitalRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ObraDigitalService implements ObraDigitalUseCase {

    private final ObraDigitalRepositoryPort obraDigitalRepositoryPort;
    private final AutorRepositoryPort autorRepositoryPort;
    private final ArchivoDigitalRepositoryPort archivoDigitalRepositoryPort;

    @Override
    public ObraDigitalDomain crear(ObraDigitalDomain obra) {
        log.info("Creando nueva obra digital: {}", obra.getTitulo());
        obra.validar();

        // Validar que el autor existe si se proporciona
        if (obra.getIdAutor() != null && !autorRepositoryPort.existePorId(obra.getIdAutor())) {
            throw new BusinessException("El autor con ID " + obra.getIdAutor() + " no existe");
        }

        return obraDigitalRepositoryPort.guardar(obra);
    }

    @Override
    public ObraDigitalDomain actualizar(Integer id, ObraDigitalDomain obra) {
        log.info("Actualizando obra digital con ID: {}", id);

        if (!obraDigitalRepositoryPort.existePorId(id)) {
            throw new EntityNotFoundException("Obra Digital", id);
        }

        obra.setIdObraDigital(id);
        obra.validar();

        // Validar que el autor existe si se proporciona
        if (obra.getIdAutor() != null && !autorRepositoryPort.existePorId(obra.getIdAutor())) {
            throw new BusinessException("El autor con ID " + obra.getIdAutor() + " no existe");
        }

        return obraDigitalRepositoryPort.guardar(obra);
    }

    @Override
    public ObraDigitalDomain actualizarArchivoPrincipal(Integer idObra, Integer idArchivo) {
        log.info("Asociando archivo principal {} a obra digital {}", idArchivo, idObra);

        ObraDigitalDomain obra = obraDigitalRepositoryPort.buscarPorId(idObra)
                .orElseThrow(() -> new EntityNotFoundException("Obra Digital", idObra));

        obra.setIdArchivoPrincipal(idArchivo);
        return obraDigitalRepositoryPort.guardar(obra);
    }

    @Override
    public void eliminar(Integer id) {
        log.info("Eliminando obra digital con ID: {}", id);

        // Verificar que la obra existe
        ObraDigitalDomain obra = obraDigitalRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Obra Digital", id));

        // Paso 1: Setear idArchivoPrincipal a null para romper la referencia
        if (obra.getIdArchivoPrincipal() != null) {
            log.info("Removiendo referencia al archivo principal de la obra");
            obra.setIdArchivoPrincipal(null);
            obraDigitalRepositoryPort.guardar(obra);
        }

        // Paso 2: Eliminar todos los archivos digitales asociados a esta obra
        List<ArchivoDigitalDomain> archivosAsociados = archivoDigitalRepositoryPort.buscarPorObraDigital(id);
        log.info("Encontrados {} archivos digitales asociados, eliminándolos...", archivosAsociados.size());
        
        for (ArchivoDigitalDomain archivo : archivosAsociados) {
            archivoDigitalRepositoryPort.eliminar(archivo.getIdArchivo());
            log.debug("Archivo digital eliminado: ID {}", archivo.getIdArchivo());
        }

        // Paso 3: Ahora sí eliminar la obra digital
        obraDigitalRepositoryPort.eliminar(id);
        log.info("Obra digital eliminada exitosamente: ID {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ObraDigitalDomain> obtenerPorId(Integer id) {
        log.info("Obteniendo obra digital con ID: {}", id);
        return obraDigitalRepositoryPort.buscarPorId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ObraDigitalDomain> obtenerTodas() {
        log.info("Obteniendo todas las obras digitales");
        return obraDigitalRepositoryPort.buscarTodas();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ObraDigitalDomain> buscarPorTitulo(String titulo) {
        log.info("Buscando obras digitales por título: {}", titulo);
        return obraDigitalRepositoryPort.buscarPorTituloContiene(titulo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ObraDigitalDomain> obtenerPorAutor(Integer idAutor) {
        log.info("Obteniendo obras del autor con ID: {}", idAutor);

        if (!autorRepositoryPort.existePorId(idAutor)) {
            throw new EntityNotFoundException("Autor", idAutor);
        }

        return obraDigitalRepositoryPort.buscarPorAutor(idAutor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ObraDigitalDomain> obtenerPorCategoria(Integer idCategoria) {
        log.info("Obteniendo obras de la categoría con ID: {}", idCategoria);
        // La implementación real se hará en el repositorio usando joins
        return List.of(); // Placeholder
    }

    @Override
    @Transactional(readOnly = true)
    public List<ObraDigitalDomain> obtenerPorColeccion(Integer idColeccion) {
        log.info("Obteniendo obras de la colección con ID: {}", idColeccion);
        // La implementación real se hará en el repositorio usando joins
        return List.of(); // Placeholder
    }

    @Override
    @Transactional(readOnly = true)
    public List<ObraDigitalDomain> obtenerPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        log.info("Obteniendo obras entre {} y {}", fechaInicio, fechaFin);
        return obraDigitalRepositoryPort.buscarPorRangoFechas(fechaInicio, fechaFin);
    }
}