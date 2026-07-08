package com.utsem.app.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utsem.app.dto.ClienteDTO;
import com.utsem.app.service.ClienteService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("rutaClientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@GetMapping("listar")
	public String metodoListar(Model model) {
		model.addAttribute("mensaje", "Listado de Clientes");
		model.addAttribute("clientes", clienteService.listar());
		return "Cliente/cliente";
	}

	@GetMapping("nuevo")
	public String metodoNuevo(Model model) {
		model.addAttribute("cliente", new ClienteDTO());
		return "Cliente/editarCliente";
	}

	@PostMapping("guardar")
	public String metodoGuarda(@Valid @ModelAttribute("cliente") ClienteDTO cliDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "Cliente/editarCliente";
		}
		clienteService.guardar(cliDto);
		return "redirect:/rutaClientes/listar";
	}

	@PostMapping("actualizar")
	public String metodoActualiza(@Valid @ModelAttribute("cliente") ClienteDTO cliDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "Cliente/editarCliente";
		}
		clienteService.actualiza(cliDto);
		return "redirect:/rutaClientes/listar";
	}

	@GetMapping("editar/{uuid}")
	public String metodoEditar(Model model, @PathVariable UUID uuid) {
		model.addAttribute("cliente", clienteService.obtenerClienteUUID(uuid));
		return "Cliente/editarCliente";
	}

	@GetMapping("eliminar/{uuid}")
	public String metodoElimina(@PathVariable UUID uuid) {
		try {
		clienteService.borrar2(uuid);
		return "redirect:/rutaClientes/listar";
		} catch (IllegalStateException ex) {
			// mostrar mensaje de error en la vista de listado
			return "redirect:/Producto/listar?error=" + java.net.URLEncoder.encode(ex.getMessage(), java.nio.charset.StandardCharsets.UTF_8);
		}
	}
}
