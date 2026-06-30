package com.utsem.app.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public class CategoriaDTO {

	@NotBlank(message = "¡Este campo es obligatorio!")
	private String nombreCategoria;
	@NotBlank(message = "¡Este campo es obligatorio!")
	private String descripcion;
	private UUID uuid;
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
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
	
	
	
}
