package org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.repository;

import org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaJpaRepository extends JpaRepository<Categoria, Integer> {

    List<Categoria> findByNombreCategoriaContainingIgnoreCase(String nombre);

    boolean existsByIdCategoria(Integer id);
}