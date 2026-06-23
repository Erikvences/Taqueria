package com.utsem.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utsem.app.model.Categoria;
import java.util.List;
import java.util.UUID;


public interface CategoriaRepo extends JpaRepository<Categoria, Long>{
	Optional<Categoria> findByUuid(UUID uuid);
	
	void deleteByUuid(UUID uuid);

}
