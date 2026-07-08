package com.utsem.app.dto;

import java.util.UUID;

import com.utsem.app.enums.Estatus;
import com.utsem.app.model.Cliente;
import jakarta.validation.constraints.NotNull;

public class PedidoDTO {

	private Float total;

	@NotNull(message = "Debe seleccionar un estatus")
	private Estatus estatus;

	private UUID uuid;

	@NotNull(message = "Debe seleccionar un cliente")
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
