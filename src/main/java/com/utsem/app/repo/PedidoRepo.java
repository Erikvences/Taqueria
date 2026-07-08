package com.utsem.app.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.utsem.app.model.Pedido;

@Repository
public interface PedidoRepo extends JpaRepository<Pedido, Long> {

	Optional<Pedido> findByUuid(UUID uuid);

	void deleteByUuid(UUID uuid);
	
	long countByCliente(com.utsem.app.model.Cliente cliente);

	@Query("SELECT p.fecha, COUNT(p), SUM(p.total) " +
		"FROM Pedido p " +
		"WHERE p.estatus = com.utsem.app.enums.Estatus.Entregado " +
		"AND (:desde IS NULL OR p.fecha >= :desde) " +
		"AND (:hasta IS NULL OR p.fecha <= :hasta) " +
		"GROUP BY p.fecha " +
		"ORDER BY p.fecha")
	List<Object[]> ventasPorFecha(@Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);
}