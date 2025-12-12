package org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.repository;

import org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.entity.ObraCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObraCategoriaJpaRepository extends JpaRepository<ObraCategoria, Integer> {

    List<ObraCategoria> findByIdCategoria(Integer idCategoria);

    List<ObraCategoria> findByIdObraDigital(Integer idObraDigital);

    boolean existsByIdObraDigitalAndIdCategoria(Integer idObraDigital, Integer idCategoria);

    @Modifying
    @Query("DELETE FROM ObraCategoria oc WHERE oc.idObraDigital = :idObraDigital AND oc.idCategoria = :idCategoria")
    void deleteByIdObraDigitalAndIdCategoria(
            @Param("idObraDigital") Integer idObraDigital,
            @Param("idCategoria") Integer idCategoria
    );
}