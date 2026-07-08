package com.utsem.app.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utsem.app.dto.PedidoDTO;
import com.utsem.app.enums.Estatus;
import com.utsem.app.model.Cliente;
import com.utsem.app.model.DetallePedido;
import com.utsem.app.model.Pedido;
import com.utsem.app.repo.ClienteRepo;
import com.utsem.app.repo.DetallePedidoRepo;
import com.utsem.app.repo.PedidoRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PedidoService {

	@Autowired
	PedidoRepo pedidoRepo;

	@Autowired
	ClienteRepo clienteRepo;

	@Autowired
	DetallePedidoRepo detallePedidoRepo;

	@Autowired
	ModelMapper mapper;

	public List<PedidoDTO> listar() {
		return pedidoRepo.findAll().stream()
				.map(pedido -> mapper.map(pedido, PedidoDTO.class))
				.toList();
	}

	public Pedido guardar(PedidoDTO pedidoDTO) {

		Pedido pedido = mapper.map(pedidoDTO, Pedido.class);

		Cliente cliente = clienteRepo.findById(pedidoDTO.getCliente().getIdCliente())
				.orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

		pedido.setCliente(cliente);

		if (pedido.getTotal() == null) {
			pedido.setTotal(0F);
		}

		if (pedido.getEstatus() == null) {
			pedido.setEstatus(Estatus.Pendiente);
		}

		return pedidoRepo.save(pedido);
	}

	public void actualizar(PedidoDTO pedidoDTO) {

		Optional<Pedido> optPedido = pedidoRepo.findByUuid(pedidoDTO.getUuid());

		if (optPedido.isPresent()) {

			Pedido pedido = optPedido.get();

			Cliente cliente = clienteRepo.findById(pedidoDTO.getCliente().getIdCliente())
					.orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

			pedido.setCliente(cliente);
			pedido.setEstatus(pedidoDTO.getEstatus());
			pedido.setGustaDaffneeAraniV(pedidoDTO.getGustaDaffneeAraniV());
			pedido.setGustaErikVD(pedidoDTO.getGustaErikVD());
			pedido.setGustaFernandoDavidSM(pedidoDTO.getGustaFernandoDavidSM());
			pedido.setGustaLuisAngelOS(pedidoDTO.getGustaLuisAngelOS());

			pedidoRepo.save(pedido);

		} else {
			throw new EntityNotFoundException("Pedido no encontrado con UUID: " + pedidoDTO.getUuid());
		}
	}

	public PedidoDTO obtenerPedidoUUID(UUID uuid) {

		Optional<Pedido> optPedido = pedidoRepo.findByUuid(uuid);

		if (optPedido.isPresent()) {
			return mapper.map(optPedido.get(), PedidoDTO.class);
		} else {
			throw new EntityNotFoundException("Pedido no encontrado con UUID: " + uuid);
		}
	}

	public Pedido obtenerPedidoEntidadUUID(UUID uuid) {
		return pedidoRepo.findByUuid(uuid)
				.orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado con UUID: " + uuid));
	}

	public void recalcularTotal(Pedido pedido) {

		List<DetallePedido> detalles = detallePedidoRepo.findByPedido(pedido);

		Float total = detalles.stream()
				.map(DetallePedido::getSubtotal)
				.filter(subtotal -> subtotal != null)
				.reduce(0F, Float::sum);

		pedido.setTotal(total);
		pedidoRepo.save(pedido);
	}

	@Transactional
	public void borrar2(UUID uuid) {

		Pedido pedido = pedidoRepo.findByUuid(uuid)
				.orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado con UUID: " + uuid));

		detallePedidoRepo.deleteByPedido(pedido);
		pedidoRepo.delete(pedido);
	}
}