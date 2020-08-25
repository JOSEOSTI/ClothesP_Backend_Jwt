package com.clotes_paradise.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clotes_paradise.crud.entity.Articulo;
import com.clotes_paradise.crud.entity.Producto;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Articulo, Integer> {
    Optional<Articulo> findByName(String name);
    boolean existsByName(String name);
}
