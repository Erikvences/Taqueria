package com.utsem.app.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utsem.app.dto.PedidoDTO;
import com.utsem.app.model.Cliente;
import com.utsem.app.model.Pedido;
import com.utsem.app.repo.ClienteRepo;
import com.utsem.app.repo.DetallePedidoRepo;
import com.utsem.app.repo.PedidoRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepo pedidoRepo;
	
	@Autowired
	private DetallePedidoRepo detallePedidoRepo;

	@Autowired
	private ClienteRepo clienteRepo;

	@Autowired
	private ModelMapper mapper;

	public List<PedidoDTO> listar() {
		return pedidoRepo.findAll().stream().map(pedido -> mapper.map(pedido, PedidoDTO.class)).toList();
	}

	public Pedido guardar(PedidoDTO pedidoDTO) {

		Pedido pedido = mapper.map(pedidoDTO, Pedido.class);

		Cliente cliente = clienteRepo.findById(pedidoDTO.getCliente().getIdCliente())
				.orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

		pedido.setCliente(cliente);

		if (pedido.getTotal() == null) {
			pedido.setTotal(0F);
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

			pedidoRepo.save(pedido);

		} else {
			throw new EntityNotFoundException("Pedido no encontrado con UUID: " + pedidoDTO.getUuid());
		}
	}

	public PedidoDTO obtenerPedidoUUID(UUID uuid) {

		Optional<Pedido> optPedido = pedidoRepo.findByUuid(uuid);

		if (optPedido.isPresent()) {
			return mapper.map(optPedido.get(), PedidoDTO.class);
		}

		throw new EntityNotFoundException("Pedido no encontrado con UUID: " + uuid);
	}

	public Pedido obtenerEntidad(UUID uuid) {

		return pedidoRepo.findByUuid(uuid)
				.orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado con UUID: " + uuid));
	}

	@Transactional
	public void borrar(UUID uuid) {

		Pedido pedido = pedidoRepo.findByUuid(uuid)
				.orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado con UUID: " + uuid));

		detallePedidoRepo.deleteByPedido(pedido);

		pedidoRepo.delete(pedido);
	}
}