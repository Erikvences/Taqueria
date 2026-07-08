package com.utsem.app.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utsem.app.model.DetallePedido;
import com.utsem.app.model.Pedido;

@Repository
public interface DetallePedidoRepo extends JpaRepository<DetallePedido, Long> {

	Optional<DetallePedido> findByUuid(UUID uuid);

	void deleteByUuid(UUID uuid);

	List<DetallePedido> findByPedido(Pedido pedido);

	void deleteByPedido(Pedido pedido);

	// Cuenta detalles que referencian un producto dado
	long countByProducto(com.utsem.app.model.Producto producto);
	
	
}