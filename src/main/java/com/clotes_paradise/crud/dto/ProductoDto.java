package com.clotes_paradise.crud.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductoDto {

    @NotBlank
    private String nombre;
    @Min(0)
    private Float precio;
    @NotBlank
	public String profileImage;

    public ProductoDto() {
    }

   

    public ProductoDto(@NotBlank String nombre, @Min(0) Float precio, @NotBlank String profileImage) {
		this.nombre = nombre;
		this.precio = precio;
		this.profileImage = profileImage;
	}



	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }



	public String getProfileImage() {
		return profileImage;
	}



	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
    
    
}
