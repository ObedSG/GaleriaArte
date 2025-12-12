package org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.repository;

import org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.entity.ObraDigital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ObraDigitalJpaRepository extends JpaRepository<ObraDigital, Integer> {

    List<ObraDigital> findByTituloContainingIgnoreCase(String titulo);

    List<ObraDigital> findByIdAutor(Integer idAutor);

    @Query("SELECT o FROM ObraDigital o WHERE o.fechaPublicacion BETWEEN :fechaInicio AND :fechaFin")
    List<ObraDigital> findByFechaPublicacionBetween(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin
    );

    @Query("SELECT o FROM ObraDigital o JOIN o.categorias c WHERE c.idCategoria = :idCategoria")
    List<ObraDigital> findByCategoria(@Param("idCategoria") Integer idCategoria);

    @Query("SELECT o FROM ObraDigital o JOIN o.colecciones c WHERE c.idColeccion = :idColeccion")
    List<ObraDigital> findByColeccion(@Param("idColeccion") Integer idColeccion);

    boolean existsByIdObraDigital(Integer id);
}