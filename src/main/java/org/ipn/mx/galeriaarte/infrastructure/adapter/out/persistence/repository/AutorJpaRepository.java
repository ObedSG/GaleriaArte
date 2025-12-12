package org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.repository;

import org.ipn.mx.galeriaarte.infrastructure.adapter.out.persistence.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorJpaRepository extends JpaRepository<Autor, Integer> {

    List<Autor> findByNombreCompletoContainingIgnoreCase(String nombre);

    boolean existsByIdAutor(Integer id);
}