package org.ipn.mx.galeriaarte.domain.port.in;

import org.ipn.mx.galeriaarte.domain.model.ObraDigitalDomain;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ObraDigitalUseCase {
    ObraDigitalDomain crear(ObraDigitalDomain obra);
    ObraDigitalDomain actualizar(Integer id, ObraDigitalDomain obra);
    ObraDigitalDomain actualizarArchivoPrincipal(Integer idObra, Integer idArchivo);
    void eliminar(Integer id);
    Optional<ObraDigitalDomain> obtenerPorId(Integer id);
    List<ObraDigitalDomain> obtenerTodas();
    List<ObraDigitalDomain> buscarPorTitulo(String titulo);
    List<ObraDigitalDomain> obtenerPorAutor(Integer idAutor);
    List<ObraDigitalDomain> obtenerPorCategoria(Integer idCategoria);
    List<ObraDigitalDomain> obtenerPorColeccion(Integer idColeccion);
    List<ObraDigitalDomain> obtenerPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin);
}