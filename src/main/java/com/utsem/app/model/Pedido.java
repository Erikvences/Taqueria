package com.utsem.app.model;

import java.util.UUID;

import com.utsem.app.enums.Estatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Float total;
	@Column
	private Estatus estatus;
	@Column(unique = true)
	private UUID uuid;
	@Column
	private String gustaDaffneeAraniV;
	@Column
	private String gustaErikVD;
	@Column
	private String gustaFernandoDavidSM;
	@Column
	private String gustaLuisAngelOS;
	@ManyToOne
	private String Producto;
	@ManyToOne
	private Long idCliente;
	
	private Long 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public Estatus getEstatus() {
		return estatus;
	}
	public void setEstatus(Estatus estatus) {
		this.estatus = estatus;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public String getGustaDaffneeAraniV() {
		return gustaDaffneeAraniV;
	}
	public void setGustaDaffneeAraniV(String gustaDaffneeAraniV) {
		this.gustaDaffneeAraniV = gustaDaffneeAraniV;
	}
	public String getGustaErikVD() {
		return gustaErikVD;
	}
	public void setGustaErikVD(String gustaErikVD) {
		this.gustaErikVD = gustaErikVD;
	}
	public String getGustaFernandoDavidSM() {
		return gustaFernandoDavidSM;
	}
	public void setGustaFernandoDavidSM(String gustaFernandoDavidSM) {
		this.gustaFernandoDavidSM = gustaFernandoDavidSM;
	}
	public String getGustaLuisAngelOS() {
		return gustaLuisAngelOS;
	}
	public void setGustaLuisAngelOS(String gustaLuisAngelOS) {
		this.gustaLuisAngelOS = gustaLuisAngelOS;
	}
	public String getProducto() {
		return Producto;
	}
	public void setProducto(String producto) {
		Producto = producto;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	
}

