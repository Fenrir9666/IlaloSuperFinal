package com.example.suculentas.service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.example.suculentas.model.DetallePedido;
import com.example.suculentas.model.Pedido;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfService {

    public byte[] generarTicket(Pedido pedido) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, baos);
            document.open();

            // 🏷️ Título principal
            Paragraph titulo = new Paragraph("Ticket de Compra - TIENDITA ITQ",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(new Paragraph(" ")); // espacio

            // 📋 Datos del pedido
            document.add(new Paragraph("Orden #: " + pedido.getId()));
            document.add(new Paragraph("Fecha emisión: " + pedido.getFecha()));
            document.add(new Paragraph("Cliente: " + pedido.getUsuario().getNombre()));
            document.add(new Paragraph("Correo: " + pedido.getUsuario().getEmail()));
            document.add(new Paragraph("Estado: " + pedido.getEstado()));
            document.add(new Paragraph(" "));

            // 🧾 Tabla de productos
            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{3, 1, 1, 1});

            // Encabezados
            tabla.addCell(celdaEncabezado("Producto"));
            tabla.addCell(celdaEncabezado("Cant"));
            tabla.addCell(celdaEncabezado("Precio"));
            tabla.addCell(celdaEncabezado("Subtotal"));

            // Filas de productos
            for (DetallePedido detalle : pedido.getDetalles()) {
                tabla.addCell(detalle.getProducto().getNombre());
                tabla.addCell(String.valueOf(detalle.getCantidad()));
                tabla.addCell("$" + detalle.getPrecioUnitario());
                double subtotal = detalle.getCantidad() * detalle.getPrecioUnitario();
                tabla.addCell("$" + subtotal);
            }

            document.add(tabla);
            document.add(new Paragraph(" "));

            // 💰 Totales
            document.add(new Paragraph("Manejo y Gestión de Envío: $15.00"));
            document.add(new Paragraph("Total a Pagar: $" + pedido.getTotal(),
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            document.add(new Paragraph(" "));

            // 🙏 Mensaje final
            Paragraph mensaje = new Paragraph("¡Gracias por tu compra! Presenta este ticket para retirar tus productos.",
                    FontFactory.getFont(FontFactory.HELVETICA, 11));
            mensaje.setAlignment(Element.ALIGN_CENTER);
            document.add(mensaje);

            document.close();
            return baos.toByteArray();

       } catch (DocumentException e) {
    e.printStackTrace();
    return null;
}

    }

    // Método auxiliar para crear celdas de encabezado
    private PdfPCell celdaEncabezado(String texto) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setBackgroundColor(Color.LIGHT_GRAY);
        return celda;
    }
}
