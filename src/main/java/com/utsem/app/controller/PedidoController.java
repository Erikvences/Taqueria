package com.utsem.app.controller;

import java.util.UUID;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping("reporte/ventas-por-fecha")
	public String reporteVentasPorFecha(
			@RequestParam(name = "desde", required = false)
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
			@RequestParam(name = "hasta", required = false)
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
			Model model) {

		var ventas = pedidoService.obtenerVentasPorFecha(desde, hasta);
		var totalVentas = ventas.stream().mapToLong(row -> ((Number) row[1]).longValue()).sum();
		var totalIngresos = ventas.stream().mapToDouble(row -> ((Number) row[2]).doubleValue()).sum();
		var promedioPorDia = ventas.isEmpty() ? 0.0 : totalIngresos / ventas.size();

		model.addAttribute("ventas", ventas);
		model.addAttribute("desde", desde);
		model.addAttribute("hasta", hasta);
		model.addAttribute("totalVentas", totalVentas);
		model.addAttribute("totalIngresos", totalIngresos);
		model.addAttribute("promedioPorDia", promedioPorDia);

		return "/Pedido/reporteVentasPorFecha";
	}

	@GetMapping("reporte/productos-mas-vendidos")
	public String reporteProductosMasVendidos(Model model) {

		var productos = pedidoService.obtenerProductosMasVendidos(10);
		model.addAttribute("productos", productos);

		return "/Pedido/reporteProductosMasVendidos";
	}

	@GetMapping("nuevo")
	public String metodoNuevo(Model model) {
		model.addAttribute("pedido", new PedidoDTO());
		model.addAttribute("clientes", clienteRepo.findAll());
		model.addAttribute("estados", Estatus.values());
		return "/Pedido/formularioPedido";
	}

	@PostMapping("guardar")
	public String metodoGuardar(@Valid @ModelAttribute("pedido") PedidoDTO pedidoDTO,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("clientes", clienteRepo.findAll());
			model.addAttribute("estados", Estatus.values());
			return "/Pedido/formularioPedido";
		}

		Pedido pedidoGuardado = pedidoService.guardar(pedidoDTO);

		// Redirect to detalle form and indicate this pedido was just created
		return "redirect:/rutaDetallePedidos/nuevo/" + pedidoGuardado.getUuid() + "?fromNew=true";
	}

	@GetMapping("editar/{uuid}")
	public String metodoEditar(@PathVariable UUID uuid, Model model) {
		model.addAttribute("pedido", pedidoService.obtenerPedidoUUID(uuid));
		model.addAttribute("clientes", clienteRepo.findAll());
		model.addAttribute("estados", Estatus.values());
		return "/Pedido/formularioPedido";
	}

	@PostMapping("actualizar")
	public String metodoActualizar(@Valid @ModelAttribute("pedido") PedidoDTO pedidoDTO, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("clientes", clienteRepo.findAll());
			model.addAttribute("estados", Estatus.values());
			return "/Pedido/formularioPedido";
		}

		pedidoService.actualizar(pedidoDTO);

		return "redirect:/rutaPedidos/listar";
	}

	@GetMapping("eliminar/{uuid}")
	public String metodoEliminar(@PathVariable UUID uuid) {
		pedidoService.borrar(uuid);
		return "redirect:/rutaPedidos/listar";
	}
}