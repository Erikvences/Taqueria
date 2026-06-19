package com.utsem.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utsem.app.model.Categoria;
import com.utsem.app.service.CategoriaService;

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
}
