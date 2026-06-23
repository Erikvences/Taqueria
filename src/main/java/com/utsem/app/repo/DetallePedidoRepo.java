package com.utsem.app.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.utsem.app.model.DetallePedido;
import com.utsem.app.model.Pedido;
public interface DetallePedidoRepo extends JpaRepository<DetallePedido, Long> {
	Optional<DetallePedido> findByUuid(UUID uuid);
	List<DetallePedido> findByPedido(Pedido pedido);

}
