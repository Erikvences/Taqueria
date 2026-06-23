package com.utsem.app.service;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.utsem.app.dto.PedidoDTO;
import com.utsem.app.model.Pedido;
import com.utsem.app.repo.PedidoRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {
	@Autowired
	PedidoRepo pedidoRepo;

	@Autowired
	ClienteRepo clienteRepo;

	@Autowired
	ModelMapper mapper;

	public List<PedidoDTO> listar() {
		return pedidoRepo.findAll().stream().map(pedido -> mapper.map(pedido, PedidoDTO.class)).toList();
	}

	public void guardar(PedidoDTO ped) {

		Pedido pedido = mapper.map(ped, Pedido.class);

		Cliente cliente = clienteRepo.findById(ped.getIdCliente())
				.orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

		pedido.setCliente(cliente);

		pedidoRepo.save(pedido);
	}

	public void actualizar(PedidoDTO ped) {

		Optional<Pedido> optPedido = pedidoRepo.findByUuid(ped.getUuid());

		if (optPedido.isPresent()) {

			Pedido pedido = optPedido.get();

			mapper.map(ped, pedido);

			Cliente cliente = clienteRepo.findById(ped.getIdCliente())
					.orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

			pedido.setCliente(cliente);

			pedidoRepo.save(pedido);

		} else {
			throw new EntityNotFoundException("Pedido no encontrado con el UUID: " + ped.getUuid());
		}
	}
}