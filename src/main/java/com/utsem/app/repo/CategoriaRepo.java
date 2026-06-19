package com.utsem.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utsem.app.model.Categoria;

public interface CategoriaRepo extends JpaRepository<Categoria, Long>{

}
