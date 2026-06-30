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
import com.utsem.app.service.ClienteService;
import com.utsem.app.service.PedidoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("rutaPedidos")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;

	@Autowired
	ClienteService clienteService;

	@GetMapping("listar")
	public String metodoListar(Model model) {

		model.addAttribute("mensaje", "Lista de Pedidos");
		model.addAttribute("pedidos", pedidoService.listar());

		return "/Pedido/paginaPedidos";
	}

	@GetMapping("nuevo")
	public String metodoNuevo(Model model) {

		model.addAttribute("pedido", new PedidoDTO());
		model.addAttribute("clientes", clienteService.listar());
		model.addAttribute("estados", Estatus.values());

		return "/Pedido/formularioPedido";
	}

	@PostMapping("guardar")
	public String metodoGuardar(@Valid @ModelAttribute("pedido") PedidoDTO pedido,
			BindingResult result,
			Model model) {

		if (result.hasErrors()) {

			model.addAttribute("clientes", clienteService.listar());
			model.addAttribute("estados", Estatus.values());

			return "/Pedido/formularioPedido";
		}

		pedidoService.guardar(pedido);

		return "redirect:/rutaPedidos/listar";
	}

	@GetMapping("editar/{uuid}")
	public String metodoEditar(Model model, @PathVariable UUID uuid) {

		model.addAttribute("pedido", pedidoService.obtenerPedidoUUID(uuid));
		model.addAttribute("clientes", clienteService.listar());
		model.addAttribute("estados", Estatus.values());

		return "/Pedido/formularioPedido";
	}

	@PostMapping("actualizar")
	public String metodoActualizar(@Valid @ModelAttribute("pedido") PedidoDTO pedido,
			BindingResult result,
			Model model) {

		if (result.hasErrors()) {

			model.addAttribute("clientes", clienteService.listar());
			model.addAttribute("estados", Estatus.values());

			return "/Pedido/formularioPedido";
		}

		pedidoService.actualizar(pedido);

		return "redirect:/rutaPedidos/listar";
	}

}

