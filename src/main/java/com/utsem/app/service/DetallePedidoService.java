package com.utsem.app.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsem.app.dto.DetallePedidoDTO;
import com.utsem.app.model.DetallePedido;
import com.utsem.app.model.Pedido;
import com.utsem.app.model.Producto;
import com.utsem.app.repo.DetallePedidoRepo;
import com.utsem.app.repo.PedidoRepo;
import com.utsem.app.repo.ProductoRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class DetallePedidoService {
	
	@Autowired
	DetallePedidoRepo detallePedidoRepo;

	@Autowired
	PedidoRepo pedidoRepo;

	@Autowired
	ProductoRepo productoRepo;

	@Autowired
	ModelMapper mapper;

	public List<DetallePedidoDTO> listarPorPedido(UUID uuidPedido) {

		Pedido pedido = pedidoRepo.findByUuid(uuidPedido)
				.orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));

		return detallePedidoRepo.findByPedido(pedido)
				.stream()
				.map(detalle -> mapper.map(detalle, DetallePedidoDTO.class))
				.toList();
	}

	public void guardar(DetallePedidoDTO dto) {

		DetallePedido detalle = new DetallePedido();

		Pedido pedido = pedidoRepo.findById(dto.getPedido().getId())
				.orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));

		Producto producto = productoRepo.findById(dto.getProducto().getId())
				.orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

		detalle.setPedido(pedido);
		detalle.setProducto(producto);
		detalle.setCantidad(dto.getCantidad());
		detalle.setTopping(dto.getTopping());

		if (producto.getPrecio() != null && dto.getCantidad() != null) {
			detalle.setSubtotal(producto.getPrecio() * dto.getCantidad());
		}

		detallePedidoRepo.save(detalle);

		recalcularTotalPedido(pedido);
	}

	public void actualizar(DetallePedidoDTO dto) {

		Optional<DetallePedido> optDetalle = detallePedidoRepo.findByUuid(dto.getUuid());

		if (optDetalle.isPresent()) {

			DetallePedido detalle = optDetalle.get();

			Pedido pedido = pedidoRepo.findById(dto.getPedido().getId())
					.orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));

			Producto producto = productoRepo.findById(dto.getProducto().getId())
					.orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

			detalle.setPedido(pedido);
			detalle.setProducto(producto);
			detalle.setCantidad(dto.getCantidad());
			detalle.setTopping(dto.getTopping());

			if (producto.getPrecio() != null && dto.getCantidad() != null) {
				detalle.setSubtotal(producto.getPrecio() * dto.getCantidad());
			}

			detallePedidoRepo.save(detalle);

			recalcularTotalPedido(pedido);

		} else {
			throw new EntityNotFoundException("DetallePedido no encontrado con UUID: " + dto.getUuid());
		}
	}

	public DetallePedidoDTO obtenerDetallePedidoUUID(UUID uuid) {

		Optional<DetallePedido> optDetalle = detallePedidoRepo.findByUuid(uuid);

		if (optDetalle.isPresent()) {
			return mapper.map(optDetalle.get(), DetallePedidoDTO.class);
		}

		throw new EntityNotFoundException("DetallePedido no encontrado con UUID: " + uuid);
	}

	public DetallePedido obtenerEntidad(UUID uuid) {
		return detallePedidoRepo.findByUuid(uuid)
				.orElseThrow(() -> new EntityNotFoundException("DetallePedido no encontrado con UUID: " + uuid));
	}

	@Transactional
	public UUID borrar(UUID uuid) {

		DetallePedido detalle = detallePedidoRepo.findByUuid(uuid)
				.orElseThrow(() -> new EntityNotFoundException("DetallePedido no encontrado con UUID: " + uuid));

		Pedido pedido = detalle.getPedido();
		UUID uuidPedido = pedido.getUuid();

		detallePedidoRepo.delete(detalle);

		recalcularTotalPedido(pedido);

		return uuidPedido;
	}

	public void recalcularTotalPedido(Pedido pedido) {

		List<DetallePedido> detalles = detallePedidoRepo.findByPedido(pedido);

		Float total = detalles.stream()
				.map(DetallePedido::getSubtotal)
				.filter(subtotal -> subtotal != null)
				.reduce(0F, Float::sum);

		pedido.setTotal(total);
		pedidoRepo.save(pedido);
	}
}