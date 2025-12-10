package org.ipn.mx.galeriaarte.domain.port.out;

import org.ipn.mx.galeriaarte.domain.model.ObraDigitalDomain;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ObraDigitalRepositoryPort {
    ObraDigitalDomain guardar(ObraDigitalDomain obra);
    void eliminar(Integer id);
    Optional<ObraDigitalDomain> buscarPorId(Integer id);
    List<ObraDigitalDomain> buscarTodas();
    List<ObraDigitalDomain> buscarPorTituloContiene(String titulo);
    List<ObraDigitalDomain> buscarPorAutor(Integer idAutor);
    List<ObraDigitalDomain> buscarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin);
    boolean existePorId(Integer id);
}