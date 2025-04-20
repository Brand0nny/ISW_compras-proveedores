package com.isw.compras_proveedores.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isw.compras_proveedores.controller.OrdenCompraController;
import com.isw.compras_proveedores.model.DetalleOrdenCompra;
import com.isw.compras_proveedores.model.Factura;
import com.isw.compras_proveedores.model.OrdenCompra;
import com.isw.compras_proveedores.model.State;
import com.isw.compras_proveedores.repository.FacturaRepository;
import com.isw.compras_proveedores.repository.OrdenCompraRepository;
import com.isw.compras_proveedores.service.OrdenCompraService;

@Service
public class OrdenCompraImpl implements OrdenCompraService {
    @Autowired
    FacturaRepository facturaRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    OrdenCompraRepository ordenCompraRepository;

    @Override
    public void enviarOrdenPorCorreo(Long ordenCompraId) {
        Optional<OrdenCompra> ordenCompraOptional = ordenCompraRepository.findById(ordenCompraId);
        if (ordenCompraOptional.isPresent()) {
            OrdenCompra ordenCompra = ordenCompraOptional.get();
            String asunto = "Nueva orden de compra " + ordenCompra.getOrderNumber();

            StringBuilder html = new StringBuilder();
            String email = ordenCompra.getProveedor().getEmail();
            System.out.println("============" + email);
            html.append("<h2>Orden de Compra</h2>");
            html.append("<p>Número de orden: ").append(ordenCompra.getOrderNumber()).append("</p>");
            html.append("<p>Fecha: ").append(ordenCompra.getOrderDate()).append("</p>");
            html.append("<p>Aprobada por: ").append(ordenCompra.getAprovedBy()).append("</p>");
            html.append("<h3>Materiales:</h3>");
            html.append("<ul>");
            for (DetalleOrdenCompra detalle : ordenCompra.getDetalles()) {
                html.append("<li>")
                        .append(detalle.getQuantity()).append(" x ")
                        .append(detalle.getMaterial().getName())
                        .append(" - $").append(detalle.getUnitPrice())
                        .append("</li>");
            }
            html.append("</ul>");
            html.append("<p>Confirmar la venta</p>");
            html.append("<a href=\"http://localhost:1818/ordenes/aprobar/")
                    .append(ordenCompra.getOrdenCompraId())
                    .append("\">Aprobar Orden</a><br>");
            html.append("<a href=\"http://localhost:1818/ordenes/rechazar/")
                    .append(ordenCompra.getOrdenCompraId())
                    .append("\">Rechazar Orden</a>");
            html.append("<p>Gracias por su atención.</p>");

            emailService.sendEmail(email, asunto, html.toString());

            ordenCompra.setStatus(State.EN_PROCESO.name());
            ordenCompraRepository.save(ordenCompra);
        }
    }

    @Override
    public void aprobarOrdenPorCorreo(Long id) {
        Optional<OrdenCompra> ordenCompraOptional = ordenCompraRepository.findById(id);
        if (ordenCompraOptional.isPresent()) {
            OrdenCompra ordenCompra = ordenCompraOptional.get();
            ordenCompra.setStatus(State.APROBADA.name());
            ordenCompraRepository.save(ordenCompra);
            String asunto = "VENTA DE " + ordenCompra.getOrderNumber();
            StringBuilder html = new StringBuilder();
            String email = ordenCompra.getProveedor().getEmail();
            System.out.println("============" + email);
            html.append("<h2>Orden de Compra</h2>");
            html.append("<p>Número de orden: ").append(ordenCompra.getOrderNumber()).append("</p>");
            html.append("<p>Fecha: ").append(ordenCompra.getOrderDate()).append("</p>");
            html.append("<h3>Materiales:</h3>");
            html.append("<ul>");
            for (DetalleOrdenCompra detalle : ordenCompra.getDetalles()) {
                html.append("<li>")
                        .append(detalle.getQuantity()).append(" x ")
                        .append(detalle.getMaterial().getName())
                        .append(" - $").append(detalle.getUnitPrice())
                        .append("</li>");
            }
            html.append("</ul>");
            html.append("<p>Por favor, notificar el envio del material mediante este enlace</p>");
            html.append("<a href=\"http://localhost:1818/ordenes/enviada/")
                    .append(ordenCompra.getOrdenCompraId())
                    .append("\">Notificar envio de Orden</a><br>");
            html.append("<p>Gracias por su atención.</p>");

            emailService.sendEmail(email, asunto, html.toString());

            

        }
    }

    @Override
    public void rechazarOrdenPorCorreo(Long id) {
        Optional<OrdenCompra> ordenCompraOptional = ordenCompraRepository.findById(id);
        if (ordenCompraOptional.isPresent()) {
            OrdenCompra ordenCompra = ordenCompraOptional.get();
            ordenCompra.setStatus(State.RECHAZADA.name());
            ordenCompraRepository.save(ordenCompra);
        }
    }
    @Override
    public void notificarEnvioOrden(Long id){
        Optional<OrdenCompra> ordenCompraOptional = ordenCompraRepository.findById(id);
        if(ordenCompraOptional.isPresent()){
            OrdenCompra ordenCompra = ordenCompraOptional.get();
            ordenCompra.setStatus(State.ENVIADO.name());
            ordenCompraRepository.save(ordenCompra);
        }
    }

    @Override
    public Factura confirmarRecepcion(Long ordenId) {
        OrdenCompra orden = ordenCompraRepository.findById(ordenId)
                          .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        orden.setStatus(State.RECIBIDO.name());
        Factura factura = new Factura();
        String in = orden.getOrderNumber()+orden.getOrdenCompraId().toString();
        factura.setOrdenCompra(orden);
        factura.setInvoiceNumber(in);
        factura.setInvoiceDate(new Date());
        factura.setStatus(State.GENERADA.name());
        factura.setAmount(BigDecimal.valueOf(calcularTotal(orden.getDetalles())));

        ordenCompraRepository.save(orden);
        return facturaRepository.save(factura);

    }
    private Double calcularTotal(List<DetalleOrdenCompra> detalles) {
        return detalles.stream()
            .mapToDouble(d -> d.getQuantity() * d.getUnitPrice())
            .sum();
    }

}
