package com.utsem.app.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsem.app.dto.ProductoDTO;
import com.utsem.app.model.Categoria;
import com.utsem.app.model.Producto;
import com.utsem.app.model.TipoCarne;
import com.utsem.app.repo.CategoriaRepo;
import com.utsem.app.repo.ProductoRepo;
import com.utsem.app.repo.TipoCarneRepo;
import com.utsem.app.repo.DetallePedidoRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProductoService {
	
	@Autowired
	ProductoRepo productoRepo;

	@Autowired
	CategoriaRepo categoriaRepo;

	@Autowired
	TipoCarneRepo tipoCarneRepo;

	@Autowired
	ModelMapper mapper;
    
	@Autowired
	DetallePedidoRepo detallePedidoRepo;
	
	public List<ProductoDTO> listar(){
		return productoRepo.findAll().stream()
				.map(producto -> mapper.map(producto, ProductoDTO.class))
				.toList();
	}
	
	public void Guardar(ProductoDTO producto) {
		Producto entidad = mapper.map(producto, Producto.class);
		if (producto.getCategoria() != null && producto.getCategoria().getUuid() != null) {
			Categoria categoriaPersistida = categoriaRepo.findByUuid(producto.getCategoria().getUuid())
				.orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con UUID: " + producto.getCategoria().getUuid()));
			entidad.setCategoria(categoriaPersistida);
		}
		if (producto.getTipoCarne() != null && producto.getTipoCarne().getUuid() != null) {
			TipoCarne tipoCarnePersistido = tipoCarneRepo.findByUuid(producto.getTipoCarne().getUuid())
				.orElseThrow(() -> new EntityNotFoundException("Tipo de carne no encontrado con UUID: " + producto.getTipoCarne().getUuid()));
			entidad.setTipoCarne(tipoCarnePersistido);
		}
		productoRepo.save(entidad);
	}
	
	public void Actualizar(ProductoDTO producto) {
		Optional<Producto> OptProducto = productoRepo.findByUuid(producto.getUuid());
		if (OptProducto.isPresent()) {
			Producto entidad = OptProducto.get();
			mapper.map(producto, entidad);
			if (producto.getCategoria() != null && producto.getCategoria().getUuid() != null) {
				Categoria categoriaPersistida = categoriaRepo.findByUuid(producto.getCategoria().getUuid())
					.orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con UUID: " + producto.getCategoria().getUuid()));
				entidad.setCategoria(categoriaPersistida);
			}
			if (producto.getTipoCarne() != null && producto.getTipoCarne().getUuid() != null) {
				TipoCarne tipoCarnePersistido = tipoCarneRepo.findByUuid(producto.getTipoCarne().getUuid())
					.orElseThrow(() -> new EntityNotFoundException("Tipo de carne no encontrado con UUID: " + producto.getTipoCarne().getUuid()));
				entidad.setTipoCarne(tipoCarnePersistido);
			}
			productoRepo.save(entidad);
		}
		else {
			throw new EntityNotFoundException("Producto no encontrada con el UUID: " + producto.getUuid());
		}
	}
	
	public ProductoDTO obtenerProductoUUID(UUID uuid) {
		Optional<Producto> optProducto = productoRepo.findByUuid(uuid);
		if (optProducto.isPresent()) {
			return mapper.map(optProducto.get(), ProductoDTO.class);
		}
		else {
			throw new EntityNotFoundException("Producto no encontrado con el UUID: " + uuid);
		}
		
	}
	
	@Transactional
	public void borrar2(UUID uuid) {
		// buscar producto
		Producto producto = productoRepo.findByUuid(uuid)
				.orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con UUID: " + uuid));
		// verificar si está relacionado a detalles de pedidos
		long referencias = detallePedidoRepo.countByProducto(producto);
		if (referencias > 0) {
			throw new IllegalStateException("No se puede eliminar el producto porque está asociado a pedidos.");
		}
		productoRepo.deleteByUuid(uuid);
	}
}
