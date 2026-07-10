package com.utsem.app.dto;

import java.util.Random;
import java.util.UUID;

import com.utsem.app.enums.Estatus;
import com.utsem.app.model.Cliente;
import jakarta.validation.constraints.NotNull;

public class PedidoDTO {

	private Float total;

	private Estatus estatus;

	private UUID uuid;

	@NotNull(message = "Debe seleccionar un cliente")
	private Cliente cliente;

	private String gustaErikVD;
	private String gustaLuisAngelOS;
	private String gustaDaffneeAraniV;
	private String gustaFernandoDavidSM;

	public String getGustaRandom() {
		String[] nombres = {
			"ErikVD",
			"LuisAngelOS",
			"DaffneeAraniV",
			"FernandoDavidSM"
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

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
