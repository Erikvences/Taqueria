package com.utsem.app.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nombreProducto;
	@Column
	private Float precio;
	@Column
	private String descripcion;
	@Column(unique = true)
	private UUID uuid;
	@ManyToOne
	private Categoria categoria;
	@ManyToOne
	private TipoCarne tipoCarne;
	@Column
	private String gustaErikVD;
	@Column
	private String gustaLuisAngelOS;
	@Column
	private String gustaDaffneeAraniV;
	@Column
	private String gustaFernandoDavidSM;
	
	@PrePersist
    private void inicializarUuid() {
        this.uuid = UUID.randomUUID();
    }
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
