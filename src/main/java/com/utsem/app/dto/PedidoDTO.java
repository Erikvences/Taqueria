package com.utsem.app.dto;

import java.util.UUID;

import com.utsem.app.enums.Estatus;
import com.utsem.app.model.Cliente;
import com.utsem.app.model.Producto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PedidoDTO {

	private Float total;
	private Estatus estatus;
	private UUID uuid;
	private String gustaDaffneeAraniV;
	private String gustaErikVD;
	private String gustaFernandoDavidSM;
	private String gustaLuisAngelOS;
	private Cliente cliente;
	
	
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
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
}
