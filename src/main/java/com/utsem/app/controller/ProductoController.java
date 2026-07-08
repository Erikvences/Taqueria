package com.utsem.app.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utsem.app.dto.ProductoDTO;
import com.utsem.app.model.Categoria;
import com.utsem.app.model.TipoCarne;
import com.utsem.app.service.CategoriaService;
import com.utsem.app.service.ProductoService;
import com.utsem.app.service.TipoCarneService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("Producto")
public class ProductoController {
	
	@Autowired
	ProductoService productoService;

	@Autowired
	CategoriaService categoriaService;

	@Autowired
	TipoCarneService tipoCarneService;

	@GetMapping("listar")
	public String Listar(Model model) {
		model.addAttribute("Productos", productoService.listar());
		return "/Productos/productos";
	}
	
	private void cargarDatosProducto(Model model) {
		model.addAttribute("categorias", categoriaService.listar());
		model.addAttribute("tiposCarne", tipoCarneService.listar());
	}

	@GetMapping("Nuevo")
	public String Nuevo(Model model) {
		ProductoDTO producto = new ProductoDTO();
		producto.setCategoria(new Categoria());
		producto.setTipoCarne(new TipoCarne());
		model.addAttribute("Producto", producto);
		cargarDatosProducto(model);
		return "/Productos/formularioProducto";
	}
	
	
	@PostMapping("guardar")
	public String Guarda(@Valid  @ModelAttribute("Producto") ProductoDTO producto, BindingResult result, Model model) {
		if(result.hasErrors()) {
			cargarDatosProducto(model);
			return "/Productos/formularioProducto";
			
		}
		productoService.Guardar(producto);
		return "redirect:/Producto/listar";
	}
	
	@PostMapping("actualizar")
	public String Actualiza(@Valid @ModelAttribute("Producto") ProductoDTO producto, BindingResult result, Model model) {
		if(result.hasErrors()) {
			cargarDatosProducto(model);
			model.addAttribute("uuid", producto.getUuid());
			return "/Productos/formularioProducto";
			
		}
		productoService.Actualizar(producto);
		return "redirect:/Producto/listar";
	}
	
	@GetMapping("editar/{uuid}")
	public String Editar(Model model, @PathVariable UUID uuid) {

		model.addAttribute("Producto", productoService.obtenerProductoUUID(uuid));
		cargarDatosProducto(model);
		return "/Productos/formularioProducto";
	}
	
	@GetMapping("eliminar/{uuid}")
	public String metodoElimina(Model model, @PathVariable UUID uuid) {

		try {
			productoService.borrar2(uuid);
			return "redirect:/Producto/listar";
		} catch (IllegalStateException ex) {
			// mostrar mensaje de error en la vista de listado
			return "redirect:/Producto/listar?error=" + java.net.URLEncoder.encode(ex.getMessage(), java.nio.charset.StandardCharsets.UTF_8);
		}
	}
}
