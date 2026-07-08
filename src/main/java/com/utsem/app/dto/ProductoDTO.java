package com.utsem.app.dto;

import java.util.Random;
import java.util.UUID;

import com.utsem.app.model.Categoria;
import com.utsem.app.model.TipoCarne;

import jakarta.validation.constraints.NotBlank;


public class ProductoDTO {
	@NotBlank(message = "¡Este campo es obligatorio!")
	private String nombreProducto;
	@NotBlank(message = "¡Este campo es obligatorio!")
	private Float precio;
	@NotBlank(message = "¡Este campo es obligatorio!")
	private String descripcion;
	private UUID uuid;
	@NotBlank(message = "¡Este campo es obligatorio!")
	private Categoria categoria;
	@NotBlank(message = "¡Este campo es obligatorio!")
	private TipoCarne tipoCarne;
	private String gustaErikVD;
	private String gustaLuisAngelOS;
	private String gustaDaffneeAraniV;
	private String gustaFernandoDavidSM;
	
	
	
	public String getGustaRandom() {

	    String[] nombres = {
	        "Erik",
	        "Luis Ángel",
	        "Daffnee",
	        "Fernando"
	    };

	    String[] gustos = {
	        gustaErikVD,
	        gustaLuisAngelOS,
	        gustaDaffneeAraniV,
	        gustaFernandoDavidSM
	    };

	    int indice = new Random().nextInt(gustos.length);

	    return nombres[indice] + ": " + gustos[indice];
	}
	
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public TipoCarne getTipoCarne() {
		return tipoCarne;
	}
	public void setTipoCarne(TipoCarne tipoCarne) {
		this.tipoCarne = tipoCarne;
	}
	public String getGustaErikVD() {
		return gustaErikVD;
	}
	public void setGustaErikVD(String gustaErikVD) {
		this.gustaErikVD = gustaErikVD;
	}
	public String getGustaLuisAngelOS() {
		return gustaLuisAngelOS;
	}
	public void setGustaLuisAngelOS(String gustaLuisAngelOS) {
		this.gustaLuisAngelOS = gustaLuisAngelOS;
	}
	public String getGustaDaffneeAraniV() {
		return gustaDaffneeAraniV;
	}
	public void setGustaDaffneeAraniV(String gustaDaffneeAraniV) {
		this.gustaDaffneeAraniV = gustaDaffneeAraniV;
	}
	public String getGustaFernandoDavidSM() {
		return gustaFernandoDavidSM;
	}
	public void setGustaFernandoDavidSM(String gustaFernandoDavidSM) {
		this.gustaFernandoDavidSM = gustaFernandoDavidSM;
	}
	
	
	
}
