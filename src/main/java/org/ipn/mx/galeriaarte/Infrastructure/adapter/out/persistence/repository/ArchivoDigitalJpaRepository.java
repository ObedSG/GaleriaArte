package org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.repository;

import org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.entity.ArchivoDigital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchivoDigitalJpaRepository extends JpaRepository<ArchivoDigital, Integer> {

    List<ArchivoDigital> findByIdObraDigital(Integer idObraDigital);

    List<ArchivoDigital> findByFormatoIgnoreCase(String formato);

    boolean existsByIdArchivo(Integer id);
}