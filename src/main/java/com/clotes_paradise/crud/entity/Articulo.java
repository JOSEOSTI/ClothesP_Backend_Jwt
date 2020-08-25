package com.clotes_paradise.crud.entity;

import javax.persistence.*;

@Entity
@Table(name = "producto")
public class Articulo {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nombre")
	private String name;
	
	@Column(name = "precio")
	private float price;

	@Column(name = "picByte", length = 100000)
	private byte[] picByte;
	
	
	
	public Articulo() {
		
	}



	public Articulo(int id, String name, float price, byte[] picByte) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.picByte = picByte;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public float getPrice() {
		return price;
	}



	public void setPrice(float price) {
		this.price = price;
	}



	public byte[] getPicByte() {
		return picByte;
	}



	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}
	

	
}