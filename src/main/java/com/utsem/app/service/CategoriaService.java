package com.utsem.app.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsem.app.dto.CategoriaDTO;
import com.utsem.app.model.Categoria;
import com.utsem.app.repo.CategoriaRepo;
import com.utsem.app.repo.ProductoRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;


@Service
public class CategoriaService {
	@Autowired
	CategoriaRepo categoriaRepo;

	@Autowired
	ModelMapper mapper;
    
	@Autowired
	ProductoRepo productoRepo;
	
	public List<CategoriaDTO> listar(){
		return categoriaRepo.findAll().stream()
				.map(categoria -> mapper.map(categoria, CategoriaDTO.class))
				.toList();
	}
	
	public void Guardar(CategoriaDTO categoria) {
		categoriaRepo.save(mapper.map(categoria, Categoria.class));
	}
	
	public void Actualizar(CategoriaDTO categoria) {
		Optional<Categoria> OptProducto = categoriaRepo.findByUuid(categoria.getUuid());
		if (OptProducto.isPresent()) {
		mapper.map(categoria, OptProducto.get());
		categoriaRepo.save(OptProducto.get());
		}
		else {
			throw new EntityNotFoundException("Producto no encontrada con el UUID: " + categoria.getUuid());
		}
	}
	
	public CategoriaDTO obtenerCategoriaUUID(UUID uuid) {
		Optional<Categoria> optCategoria = categoriaRepo.findByUuid(uuid);
		if (optCategoria.isPresent()) {
			return mapper.map(optCategoria.get(),CategoriaDTO.class);
		}
		else {
			throw new EntityNotFoundException("Categoeria no encontrada con el UUID: " + uuid);
		}
		
	}
	
	@Transactional
	public void borrar2(UUID uuid) {
		com.utsem.app.model.Categoria categoria = categoriaRepo.findByUuid(uuid)
				.orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con el UUID: " + uuid));
		long referencias = productoRepo.countByCategoria(categoria);
		if (referencias > 0) {
			throw new IllegalStateException("No se puede eliminar la categoría porque está asociada a productos.");
		}
		categoriaRepo.deleteByUuid(uuid);
	}
}
