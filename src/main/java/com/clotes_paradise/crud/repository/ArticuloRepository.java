package com.clotes_paradise.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clotes_paradise.crud.dto.ArticuloDto;
import com.clotes_paradise.crud.entity.Articulo;



public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {

	void save(ArticuloDto articulo);
}