package org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.repository;

import org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.entity.ColeccionObra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColeccionObraJpaRepository extends JpaRepository<ColeccionObra, Integer> {

    List<ColeccionObra> findByIdColeccion(Integer idColeccion);

    List<ColeccionObra> findByIdObraDigital(Integer idObraDigital);

    boolean existsByIdColeccionAndIdObraDigital(Integer idColeccion, Integer idObraDigital);

    @Modifying
    @Query("DELETE FROM ColeccionObra co WHERE co.idColeccion = :idColeccion AND co.idObraDigital = :idObraDigital")
    void deleteByIdColeccionAndIdObraDigital(
            @Param("idColeccion") Integer idColeccion,
            @Param("idObraDigital") Integer idObraDigital
    );
}