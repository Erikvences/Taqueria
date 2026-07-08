package com.utsem.app.repo;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utsem.app.model.Pedido;

@Repository
public interface PedidoRepo extends JpaRepository<Pedido, Long> {

	Optional<Pedido> findByUuid(UUID uuid);

	void deleteByUuid(UUID uuid);
	
	long countByCliente(com.utsem.app.model.Cliente cliente);
}