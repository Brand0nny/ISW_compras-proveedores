package com.isw.compras_proveedores.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isw.compras_proveedores.model.DetalleOrdenCompra;
import com.isw.compras_proveedores.model.Factura;
import com.isw.compras_proveedores.model.Pago;
import com.isw.compras_proveedores.model.State;
import com.isw.compras_proveedores.repository.FacturaRepository;
import com.isw.compras_proveedores.repository.PagoRepository;
import com.isw.compras_proveedores.service.PagoService;

@Service
public class PagoServiceImpl implements PagoService{
    @Autowired
    PagoRepository pagoRepository;
    @Autowired
    FacturaRepository facturaRepository;
    @Autowired
    private EmailService emailService;
    public static final String emailFinanzas = "finanzas@inbox.mailtrap.io";

    @Override
    public Pago pagarFactura(Long facturaId, BigDecimal paymentAmount) {
        Optional<Factura> facturaOptional = facturaRepository.findById(facturaId);
        if(facturaOptional.isPresent()){
            Pago pago = new Pago();
            System.out.println("================="+paymentAmount);
            Factura factura = facturaOptional.get();
            pago.setFactura(factura);
            pago.setPaymentAmount(paymentAmount);
            pago.setPaymentDate(new Date());
            pago.setStatus(State.PENDIENTE.name());
            pagoRepository.save(pago);
            Long pagoId = pagoRepository.findByFactura(factura).get().getPagoId();
            String asunto = "Aprobación de pago " + pago.getFactura().getInvoiceNumber();
            StringBuilder html = new StringBuilder();
            System.out.println("============" + emailFinanzas);
            html.append("<h2>Notificación de Pago</h2>");
            html.append("<p>Se ha generado un pago para la factura: ").append(pago.getFactura().getInvoiceNumber()).append("</p>");
            html.append("<p>Monto del pago: $").append(paymentAmount).append("</p>");
            html.append("<p>Fecha: ").append(pago.getPaymentDate()).append("</p>");
            html.append("<p>Para aprobar el pago, haga clic en el siguiente enlace:</p>");
            html.append("<a href=\"http://localhost:1818/finanzas/aprobar/")
                .append(pagoId)
                .append("\" style=\"display: inline-block; padding: 10px 20px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 5px;\">Aprobar Pago</a>");
            html.append("<a href=\"http://localhost:1818/finanzas/rechazar/")
                .append(pagoId)
                .append("\" style=\"display: inline-block; padding: 10px 20px; background-color: #Ff0000; color: white; text-decoration: none; border-radius: 5px;\">Aprobar Pago</a>");
    
            emailService.sendEmail(emailFinanzas, asunto, html.toString());

            System.out.println(html.toString());
            return pago;
        }
        return null;
    }

    @Override
    public void aprobarPago(Long id) {
        Optional<Pago> pagoOptional = pagoRepository.findById(id);
        if(pagoOptional.isPresent()){
            Pago pago = pagoOptional.get();
            pago.setStatus(State.APROBADA.name());
            pagoRepository.save(pago);
        }
        
    }

    @Override
    public void rechazarPago(Long id) {
        Optional<Pago> pagoOptional = pagoRepository.findById(id);
        if(pagoOptional.isPresent()){
            Pago pago = pagoOptional.get();
            pago.setStatus(State.RECHAZADA.name());
            pagoRepository.save(pago);
        }
    }
    
}
