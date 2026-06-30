package com.utsem.app.dto;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;

public class ClienteDTO {

	private UUID uuid;

	@NotBlank(message = "¡Este campo es obligatorio!")
	private String nombre;

	@NotBlank(message = "¡Este campo es obligatorio!")
	private String telefono;

	@NotBlank(message = "¡Este campo es obligatorio!")
	private String direccion;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
