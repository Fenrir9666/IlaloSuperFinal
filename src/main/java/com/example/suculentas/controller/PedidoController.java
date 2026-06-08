package com.example.suculentas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.suculentas.model.Carrito;
import com.example.suculentas.model.CarritoDetalle;
import com.example.suculentas.model.Pedido;
import com.example.suculentas.model.Usuario;
import com.example.suculentas.service.CarritoService;
import com.example.suculentas.service.PdfService;
import com.example.suculentas.service.PedidoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;
    private final PdfService pdfService;
    private final CarritoService carritoService;

    public PedidoController(PedidoService pedidoService, PdfService pdfService, CarritoService carritoService) {
        this.pedidoService = pedidoService;
        this.pdfService = pdfService;
        this.carritoService = carritoService;
    }

    // Procesar compra
   @PostMapping("/procesar")
public String procesarCompra(@AuthenticationPrincipal Usuario usuario, Model model) {
    Carrito carrito = carritoService.obtenerCarritoActivo(usuario);
    List<CarritoDetalle> detallesCarrito = carritoService.obtenerDetalles(carrito);

    Pedido pedido = pedidoService.crearPedido(usuario, detallesCarrito);
    pedidoService.confirmarPedido(pedido);
    pdfService.generarTicket(pedido);

    model.addAttribute("pedido", pedido);
    
    // 👈 Cambia esto de "confirmacion" a "confirmar" para que busque el nuevo archivo
    return "confirmar";  
}




    // Ver historial de pedidos
    @GetMapping("/historial")
    public String verHistorial(@AuthenticationPrincipal Usuario usuario, Model model) {
        List<Pedido> historial = pedidoService.obtenerHistorial(usuario);
        model.addAttribute("historial", historial);
        return "historial";
    }

    // Descargar PDF de un pedido
  @GetMapping("/pdf/{id}")
public ResponseEntity<byte[]> descargarPdf(@PathVariable Long id) {
    Pedido pedido = pedidoService.obtenerPorId(id);
    byte[] pdfBytes = pdfService.generarTicket(pedido);

    return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=ticket_" + id + ".pdf")
            .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
            .body(pdfBytes);
}

}
