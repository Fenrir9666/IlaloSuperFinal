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

    // 1. Recibe la acción del carrito, crea el pedido como "Pendiente" y redirige al visor GET
    @PostMapping("/procesar")
    public String procesarCompra(@AuthenticationPrincipal Usuario usuario) {
        Carrito carrito = carritoService.obtenerCarritoActivo(usuario);
        List<CarritoDetalle> detallesCarrito = carritoService.obtenerDetalles(carrito);

        // Nace el pedido (El servicio lo crea inicialmente en "Pendiente")
        Pedido pedido = pedidoService.crearPedido(usuario, detallesCarrito);

        // Redirecciona de forma segura a la URL de confirmación usando su ID
        return "redirect:/pedido/confirmar/" + pedido.getId();
    }

    // 2. Muestra la pantalla con los datos del pedido (Sirve para ver estado PENDIENTE o PROCESADO)
    @GetMapping("/confirmar/{id}")
    public String mostrarConfirmacion(@PathVariable Long id, Model model) {
        Pedido pedido = pedidoService.obtenerPorId(id);
        model.addAttribute("pedido", pedido);
        return "confirmar"; 
    }

    // 3. Se activa al pulsar "Confirmar Pago". Modifica el estado en la BD y refresca la misma pantalla
    @PostMapping("/confirmar-pago/{id}")
    public String confirmarPago(@PathVariable Long id) {
        Pedido pedido = pedidoService.obtenerPorId(id);
        
        // Cambia el estado a "Procesado" en la base de datos
        pedidoService.confirmarPedido(pedido);
        
        // Genera el ticket PDF con los datos actualizados
        pdfService.generarTicket(pedido);

        // Redirecciona de vuelta al visor GET para que el usuario vea el cambio reflejado al instante
        return "redirect:/pedido/confirmar/" + id;
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
