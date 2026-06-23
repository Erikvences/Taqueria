package com.utsem.app.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utsem.app.dto.TipoCarneDTO;
import com.utsem.app.service.TipoCarneService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("rutaTipoCarne")
public class TipoCarneController {

	@Autowired
	TipoCarneService tipoCarneService;

	@GetMapping("listar")
	public String metodoListar(Model model) {
		model.addAttribute("mensaje", "Listado de Tipos de Carne");
		model.addAttribute("tiposCarne", tipoCarneService.listar());
		return "TipoCarne/tipoCarne";
	}

	@GetMapping("nuevo")
	public String metodoNuevo(Model model) {
		model.addAttribute("tipoCarne", new TipoCarneDTO());
		return "TipoCarne/editarTipoCarne";
	}

	@PostMapping("guardar")
	public String metodoGuarda(@Valid @ModelAttribute("tipoCarne") TipoCarneDTO tcDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "TipoCarne/editarTipoCarne";
		}
		tipoCarneService.guardar(tcDto);
		return "redirect:/rutaTipoCarne/listar";
	}

	@PostMapping("actualizar")
	public String metodoActualiza(@Valid @ModelAttribute("tipoCarne") TipoCarneDTO tcDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "TipoCarne/editarTipoCarne";
		}
		tipoCarneService.actualiza(tcDto);
		return "redirect:/rutaTipoCarne/listar";
	}

	@GetMapping("editar/{uuid}")
	public String metodoEditar(Model model, @PathVariable UUID uuid) {
		model.addAttribute("tipoCarne", tipoCarneService.obtenerTipoCarneUUID(uuid));
		return "TipoCarne/editarTipoCarne";
	}

	@GetMapping("eliminar/{uuid}")
	public String metodoElimina(@PathVariable UUID uuid) {
		tipoCarneService.borrar(uuid);
		return "redirect:/rutaTipoCarne/listar";
	}
}
