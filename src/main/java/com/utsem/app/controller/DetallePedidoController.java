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

import com.utsem.app.dto.DetallePedidoDTO;
import com.utsem.app.model.Pedido;
import com.utsem.app.repo.ProductoRepo;
import com.utsem.app.service.DetallePedidoService;
import com.utsem.app.service.PedidoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("rutaDetallePedidos")
public class DetallePedidoController {
	@Autowired
	DetallePedidoService detallePedidoService;

	@Autowired
	PedidoService pedidoService;

	@Autowired
	ProductoRepo productoRepo;

	@GetMapping("pedido/{uuidPedido}")
	public String listarPorPedido(@PathVariable UUID uuidPedido, Model model) {

		Pedido pedido = pedidoService.obtenerEntidad(uuidPedido);

		model.addAttribute("pedido", pedido);
		model.addAttribute("uuidPedido", uuidPedido);
		model.addAttribute("detalles", detallePedidoService.listarPorPedido(uuidPedido));

		return "/DetallePedido/detallePedido";
	}

	@GetMapping("nuevo/{uuidPedido}")
	public String nuevo(@PathVariable UUID uuidPedido, Model model) {

		Pedido pedido = pedidoService.obtenerEntidad(uuidPedido);

		DetallePedidoDTO detallePedido = new DetallePedidoDTO();
		detallePedido.setPedido(pedido);

		model.addAttribute("detallePedido", detallePedido);
		model.addAttribute("pedido", pedido);
		model.addAttribute("productos", productoRepo.findAll());

		return "/DetallePedido/formularioDetallePedido";
	}

	@PostMapping("guardar")
	public String guardar(@Valid @ModelAttribute("detallePedido") DetallePedidoDTO detallePedido,
			BindingResult result, Model model) {

		Pedido pedido = pedidoService.obtenerEntidad(detallePedido.getPedido().getUuid());

		if (result.hasErrors()) {
			model.addAttribute("pedido", pedido);
			model.addAttribute("productos", productoRepo.findAll());
			return "/DetallePedido/formularioDetallePedido";
		}

		detallePedidoService.guardar(detallePedido);

		return "redirect:/rutaDetallePedidos/pedido/" + pedido.getUuid();
	}

	@GetMapping("editar/{uuid}")
	public String editar(@PathVariable UUID uuid, Model model) {

		DetallePedidoDTO detallePedido = detallePedidoService.obtenerDetallePedidoUUID(uuid);

		model.addAttribute("detallePedido", detallePedido);
		model.addAttribute("pedido", detallePedido.getPedido());
		model.addAttribute("productos", productoRepo.findAll());

		return "/DetallePedido/formularioDetallePedido";
	}

	@PostMapping("actualizar")
	public String actualizar(@Valid @ModelAttribute("detallePedido") DetallePedidoDTO detallePedido,
			BindingResult result, Model model) {

		Pedido pedido = pedidoService.obtenerEntidad(detallePedido.getPedido().getUuid());

		if (result.hasErrors()) {
			model.addAttribute("pedido", pedido);
			model.addAttribute("productos", productoRepo.findAll());
			return "/DetallePedido/formularioDetallePedido";
		}

		detallePedidoService.actualizar(detallePedido);

		return "redirect:/rutaDetallePedidos/pedido/" + pedido.getUuid();
	}

	@GetMapping("eliminar/{uuid}")
	public String eliminar(@PathVariable UUID uuid) {

		UUID uuidPedido = detallePedidoService.borrar(uuid);

		return "redirect:/rutaDetallePedidos/pedido/" + uuidPedido;
	}
}