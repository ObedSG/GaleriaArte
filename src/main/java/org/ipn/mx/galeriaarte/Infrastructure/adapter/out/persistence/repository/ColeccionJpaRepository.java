package org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.repository;

import org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.entity.Coleccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ColeccionJpaRepository extends JpaRepository<Coleccion, Integer> {

    List<Coleccion> findByNombreColeccionContainingIgnoreCase(String nombre);

    @Query("SELECT c FROM Coleccion c WHERE " +
            "(c.fechaInicio IS NULL OR c.fechaInicio <= :fecha) AND " +
            "(c.fechaFin IS NULL OR c.fechaFin >= :fecha)")
    List<Coleccion> findColeccionesActivas(@Param("fecha") LocalDate fecha);

    boolean existsByIdColeccion(Integer id);
}