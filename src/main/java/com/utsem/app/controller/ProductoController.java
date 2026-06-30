package com.utsem.app.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utsem.app.dto.ProductoDTO;
import com.utsem.app.service.ProductoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("Producto")
public class ProductoController {
	
	@Autowired
	ProductoService productoService;

	@GetMapping("listar")
	public String Listar(Model model) {
		model.addAttribute("Productos", productoService.listar());
		return "/Producto/producto";
	}
	
	@GetMapping("Nuevo")
	public String Nuevo(Model model) {
		model.addAttribute("Producto", new ProductoDTO());
		return "/Producto/formularioProducto";
	}
	
	
	@PostMapping("guardar")
	public String Guarda(@Valid  @ModelAttribute("Producto") ProductoDTO producto, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "/Producto/formularioProducto";
			
		}
		productoService.Guardar(producto);
		return "redirect:/Producto/listar";
	}
	
	@PostMapping("actualizar")
	public String Actualiza(@Valid @ModelAttribute("Producto") ProductoDTO producto, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("uuid", producto.getUuid());
			return "/Producto/formularioProducto";
			
		}
		productoService.Actualizar(producto);
		return "redirect:/Producto/listar";
	}
	
	@GetMapping("editar/{uuid}")
	public String Editar(Model model, @PathVariable UUID uuid) {

		model.addAttribute("Producto", productoService.obtenerProductoUUID(uuid));
		return "/Producto/formularioProducto";
	}
	
	@GetMapping("eliminar/{uuid}")
	public String metodoElimina(Model model, @PathVariable UUID uuid) {

		
		productoService.borrar2(uuid);
		return "redirect:/Producto/listar";
	}
}
