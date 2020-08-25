package com.clotes_paradise.crud.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.clotes_paradise.crud.dto.ArticuloDto;
import com.clotes_paradise.crud.dto.Mensaje;
import com.clotes_paradise.crud.dto.ProductoDto;
import com.clotes_paradise.crud.entity.Articulo;
import com.clotes_paradise.crud.entity.Producto;
import com.clotes_paradise.crud.repository.ArticuloRepository;
import com.clotes_paradise.crud.repository.ProductoRepository;
import com.clotes_paradise.crud.service.ProductoService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = { Constantes.DOMAINS_1, Constantes.DOMAINS_2, Constantes.DOMAINS_3, Constantes.DOMAINS_4,
		Constantes.DOMAINS_5, Constantes.DOMAINS_6 })
public class ProductoController {
	
	
	private byte[] bytes;
	@Autowired
	private JdbcTemplate jdbcTemplate;
    @Autowired
    ProductoService productoService;
    @Autowired
	private ArticuloRepository  articuloRepository;
    
    @PostMapping("/upload")
	public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		this.bytes = file.getBytes();
	}
    
    @GetMapping("/lista")
    public ResponseEntity<List<Articulo>> list(){
        List<Articulo> list = productoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
  /* @GetMapping("/lista")
	public List<Articulo> getBooks() {
		return articuloRepository.findAll();
	}*/
    @RequestMapping(value = "allProducto/", method = RequestMethod.GET)
	public List<Producto> getUsuarioById() {
		return jdbcTemplate
				.query("SELECT * FROM producto", new BeanPropertyRowMapper<>(Producto.class));
}
   @GetMapping("/detail/{id}")
    public ResponseEntity<Articulo> getById(@PathVariable("id") int id){
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Articulo articulo = productoService.getOne(id).get();
        return new ResponseEntity(articulo, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Articulo> getByName(@PathVariable("nombre") String name){
        if(!productoService.existsByName(name))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Articulo articulo = productoService.getByName(name).get();
        return new ResponseEntity(articulo, HttpStatus.OK);
    }

   /*@GetMapping("/detailname/{id}")
    public List<Articulo> getByNombre(@PathVariable("id") String id){
  		return jdbcTemplate
  				.query("SELECT * FROM producto WHERE id='" + id + "' :", new BeanPropertyRowMapper<>(Articulo.class));
  }*/
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
	public void createBook(@RequestBody  Articulo articulo) throws IOException {
    	articulo.setPicByte(this.bytes);
		articuloRepository.save(articulo);
		this.bytes = null;
	}
    /*@PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductoDto productoDto){
        if(StringUtils.isBlank(productoDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(productoDto.getPrecio()==null || productoDto.getPrecio()<0 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(productoService.existsByNombre(productoDto.getNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio() , productoDto.getProfileImage());
        System.out.println(producto);
        productoService.save(producto);
        
        return new ResponseEntity(new Mensaje("producto creado"), HttpStatus.OK);
    }*/

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ArticuloDto articuloDto){
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(productoService.existsByName(articuloDto.getName()) && productoService.getByName(articuloDto.getName()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(articuloDto.getName()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(articuloDto.getPrice()==null || articuloDto.getPrice()<0 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        Articulo articulo = productoService.getOne(id).get();
        articulo.setName(articuloDto.getName());
        articulo.setPrice(articuloDto.getPrice());
        productoService.save(articulo);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        productoService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }


}
