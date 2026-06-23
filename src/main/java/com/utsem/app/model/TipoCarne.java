package com.utsem.app.model;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class TipoCarne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipo;

	@Column(unique = true)
	private UUID uuid;

	@Column
	private String nombre;

	@PrePersist
	private void inicializarUuid() {
		this.uuid = UUID.randomUUID();
	}

	public Long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

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
