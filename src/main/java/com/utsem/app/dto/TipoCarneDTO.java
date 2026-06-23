package com.utsem.app.dto;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;

public class TipoCarneDTO {

	private UUID uuid;

	@NotBlank(message = "¡Este campo es obligatorio!")
	private String nombre;

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
}
