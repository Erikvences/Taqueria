package com.utsem.app.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utsem.app.model.Producto;


public interface ProductoRepo extends JpaRepository<Producto, Long> {

	Optional<Producto> findByUuid(UUID uuid);
	void deleteByUuid(UUID uuid);
}
