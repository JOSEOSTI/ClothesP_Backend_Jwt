package com.clotes_paradise.crud.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ArticuloDto {

    @NotBlank
    private String name;
    @Min(0)
    private Float price;
    @NotBlank
	private byte[] picByte;

    public ArticuloDto() {
    }


	
	public ArticuloDto(@NotBlank String name, @Min(0) Float price, @NotBlank byte[] picByte) {
		this.name = name;
		this.price = price;
		this.picByte = picByte;
	}


	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Float getPrice() {
		return price;
	}



	public void setPrice(Float price) {
		this.price = price;
	}



	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}



	
    
}
