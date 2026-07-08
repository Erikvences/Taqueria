package com.utsem.app.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsem.app.dto.ClienteDTO;
import com.utsem.app.model.Cliente;
import com.utsem.app.model.Producto;
import com.utsem.app.repo.ClienteRepo;
import com.utsem.app.repo.DetallePedidoRepo;
import com.utsem.app.repo.PedidoRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

	@Autowired
	ClienteRepo clienteRepo;
	
	@Autowired
	PedidoRepo pedidoRepo;

	@Autowired
	ModelMapper mapper;

	public List<ClienteDTO> listar() {
		return clienteRepo.findAll().stream()
				.map(cliente -> mapper.map(cliente, ClienteDTO.class))
				.toList();
	}

	public void guardar(ClienteDTO clienteDTO) {
		clienteRepo.save(mapper.map(clienteDTO, Cliente.class));
	}

	public void actualiza(ClienteDTO clienteDTO) {
		Optional<Cliente> optCliente = clienteRepo.findByUuid(clienteDTO.getUuid());
		if (optCliente.isPresent()) {
			mapper.map(clienteDTO, optCliente.get());
			clienteRepo.save(optCliente.get());
		} else {
			throw new EntityNotFoundException("Cliente no encontrado con el UUID: " + clienteDTO.getUuid());
		}
	}

	public void borrar(UUID uuid) {
		Optional<Cliente> optCliente = clienteRepo.findByUuid(uuid);
		if (optCliente.isPresent()) {
			clienteRepo.delete(optCliente.get());
		} else {
			throw new EntityNotFoundException("Cliente no encontrado con el UUID: " + uuid);
		}
	}

	@Transactional
	public void borrar2(UUID uuid) {
		// buscar producto
				Cliente cliente = clienteRepo.findByUuid(uuid)
						.orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con UUID: " + uuid));
				// verificar si está relacionado a detalles de pedidos
				long referencias = pedidoRepo.countByCliente(cliente);
				if (referencias > 0) {
					throw new IllegalStateException("No se puede eliminar el cliente porque está asociado a pedidos.");
				}
		clienteRepo.deleteByUuid(uuid);
	}

	public ClienteDTO obtenerClienteUUID(UUID uuid) {
		Optional<Cliente> optCliente = clienteRepo.findByUuid(uuid);
		if (optCliente.isPresent()) {
			return mapper.map(optCliente.get(), ClienteDTO.class);
		} else {
			throw new EntityNotFoundException("Cliente no encontrado con el UUID: " + uuid);
		}
	}
}
