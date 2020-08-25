package com.clotes_paradise.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clotes_paradise.crud.entity.Articulo;
import com.clotes_paradise.crud.entity.Producto;
import com.clotes_paradise.crud.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public List<Articulo> list(){
        return productoRepository.findAll();
    }

    public Optional<Articulo> getOne(int id){
        return productoRepository.findById((int) id);
    }

    public Optional<Articulo> getByName(String name){
        return productoRepository.findByName(name);
    }

    public void  save(Articulo articulo){
        productoRepository.save(articulo);
    }

    public void delete(int id){
        productoRepository.deleteById((int) id);
    }

    public boolean existsById(int id){
        return productoRepository.existsById((int) id);
    }

    public boolean existsByName(String name) {
		// TODO Auto-generated method stub
        return productoRepository.existsByName(name);
	}
	
}
