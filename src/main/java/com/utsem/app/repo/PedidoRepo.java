package com.utsem.app.repo;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.utsem.app.model.Pedido;

public interface PedidoRepo extends JpaRepository<Pedido, Long> {Optional<Pedido> findByUuid(UUID uuid);

}
