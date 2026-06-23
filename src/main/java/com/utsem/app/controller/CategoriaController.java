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

import com.utsem.app.dto.CategoriaDTO;
import com.utsem.app.model.Categoria;
import com.utsem.app.service.CategoriaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("Categoria")
public class CategoriaController {
	@Autowired
	CategoriaService categoriaService;

	@GetMapping("listar")
	public String Listar(Model model) {
		model.addAttribute("Categorias", categoriaService.listar());
		return "/Categoria/categoria";
	}
	
	@GetMapping("Nueva")
	public String Nueva(Model model) {
		model.addAttribute("Categoria", new Categoria());
		return "/Categoria/formularioCategoria";
	}
	
	
	@PostMapping("guardar")
	public String Guarda(@Valid  @ModelAttribute("Categoria") CategoriaDTO categoria, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "/Categoria/formularioCategoria";
			
		}
		categoriaService.Guardar(categoria);
		return "redirect:/Categoria/listar";
	}
	
	@PostMapping("actualizar")
	public String Actualiza(@Valid @ModelAttribute("Categoria") CategoriaDTO categoria, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("uuid", categoria.getUuid());
			return "/Categoria/formularioCategoria";
			
		}
		categoriaService.Actualizar(categoria);
		return "redirect:/Categoria/listar";
	}
	
	@GetMapping("editar/{uuid}")
	public String Editar(Model model, @PathVariable UUID uuid) {

		model.addAttribute("Categoria", categoriaService.obtenerCategoriaUUID(uuid));
		return "/Categoria/formularioCategoria";
	}
	
	@GetMapping("eliminar/{uuid}")
	public String metodoElimina(Model model, @PathVariable UUID uuid) {

		
		categoriaService.borrar2(uuid);
		return "redirect:/Categoria/listar";
	}
	
}
