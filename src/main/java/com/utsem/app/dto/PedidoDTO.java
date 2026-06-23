package com.utsem.app.dto;

import java.util.UUID;

import com.utsem.app.enums.Estatus;

public class PedidoDTO {

	private Estatus estatus;
	private UUID uuid;
	private String cliente;
	private String gustaDaffneeAraniV;
	private String gustaErikVD;
	private String gustaFernandoDavidSM;
	private String gustaLuisAngelOS;
	
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
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
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
	
	
}
