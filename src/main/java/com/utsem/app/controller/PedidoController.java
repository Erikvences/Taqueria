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

import com.utsem.app.dto.PedidoDTO;
import com.utsem.app.enums.Estatus;
import com.utsem.app.model.Pedido;
import com.utsem.app.repo.ClienteRepo;
import com.utsem.app.service.PedidoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("rutaPedidos")
public class PedidoController {
	@Autowired
	PedidoService pedidoService;

	@Autowired
	ClienteRepo clienteRepo;

	@GetMapping("listar")
	public String metodoListar(Model model) {
		model.addAttribute("pedidos", pedidoService.listar());
		return "/Pedido/pedido";
	}

	@GetMapping("nuevo")
	public String metodoNuevo(Model model) {
		model.addAttribute("pedido", new PedidoDTO());
		model.addAttribute("clientes", clienteRepo.findAll());
		model.addAttribute("estados", Estatus.values());
		return "/Pedido/formularioPedido";
	}

	@PostMapping("guardar")
	public String metodoGuardar(@Valid @ModelAttribute("pedido") PedidoDTO pedido,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("clientes", clienteRepo.findAll());
			model.addAttribute("estados", Estatus.values());
			return "/Pedido/formularioPedido";
		}

		Pedido pedidoGuardado = pedidoService.guardar(pedido);

		return "redirect:/rutaDetallePedidos/pedido/" + pedidoGuardado.getUuid();
	}

	@GetMapping("editar/{uuid}")
	public String metodoEditar(Model model, @PathVariable UUID uuid) {
		model.addAttribute("pedido", pedidoService.obtenerPedidoUUID(uuid));
		model.addAttribute("clientes", clienteRepo.findAll());
		model.addAttribute("estados", Estatus.values());
		return "/Pedido/formularioPedido";
	}

	@PostMapping("actualizar")
	public String metodoActualizar(@Valid @ModelAttribute("pedido") PedidoDTO pedido, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("clientes", clienteRepo.findAll());
			model.addAttribute("estados", Estatus.values());
			return "/Pedido/formularioPedido";
		}

		pedidoService.actualizar(pedido);
		return "redirect:/rutaPedidos/listar";
	}

	@GetMapping("eliminar/{uuid}")
	public String metodoEliminar(@PathVariable UUID uuid) {
		pedidoService.borrar2(uuid);
		return "redirect:/rutaPedidos/listar";
	}
}