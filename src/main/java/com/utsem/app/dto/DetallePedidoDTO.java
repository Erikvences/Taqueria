package com.utsem.app.dto;

import java.util.UUID;

import com.utsem.app.model.Pedido;
import com.utsem.app.model.Producto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DetallePedidoDTO {


	private Producto producto;
	private Pedido pedido;
	private Integer cantidad;
	private Float subtotal;
	private String topping;
	private UUID uuid;
	 
	 public Producto getProducto() {
		 return producto;
	 }
	 public void setProducto(Producto producto) {
		 this.producto = producto;
	 }
	 public Pedido getPedido() {
		 return pedido;
	 }
	 public void setPedido(Pedido pedido) {
		 this.pedido = pedido;
	 }
	 public Integer getCantidad() {
		 return cantidad;
	 }
	 public void setCantidad(Integer cantidad) {
		 this.cantidad = cantidad;
	 }
	 public Float getSubtotal() {
		 return subtotal;
	 }
	 public void setSubtotal(Float subtotal) {
		 this.subtotal = subtotal;
	 }
	 public String getTopping() {
		 return topping;
	 }
	 public void setTopping(String topping) {
		 this.topping = topping;
	 }
	 public UUID getUuid() {
		 return uuid;
	 }
	 public void setUuid(UUID uuid) {
		 this.uuid = uuid;
	 }

	 

}
