package com.isw.compras_proveedores.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isw.compras_proveedores.model.Factura;
import com.isw.compras_proveedores.model.MovimientosPresupuesto;
import com.isw.compras_proveedores.model.Pago;
import com.isw.compras_proveedores.model.PresupuestoCompras;
import com.isw.compras_proveedores.model.State;
import com.isw.compras_proveedores.repository.FacturaRepository;
import com.isw.compras_proveedores.repository.MovimientosPresupuestoRepository;
import com.isw.compras_proveedores.repository.PagoRepository;
import com.isw.compras_proveedores.repository.PresupuestoComprasRepository;
import com.isw.compras_proveedores.service.impl.EmailService;
import com.isw.compras_proveedores.service.PagoService;

@Service
public class PagoServiceImpl implements PagoService {
    @Autowired
    private PagoRepository pagoRepository;
    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private PresupuestoComprasRepository presupuestoComprasRepository;
    @Autowired
    private MovimientosPresupuestoRepository movimientosPresupuestoRepository;
    @Autowired
    private EmailService emailService;

    public static final String emailFinanzas = "finanzas@inbox.mailtrap.io";

    @Override
    @Transactional
    public Pago pagarFactura(Long facturaId, BigDecimal paymentAmount) {
        Optional<Factura> facturaOpt = facturaRepository.findById(facturaId);
        if (!facturaOpt.isPresent()) {
            throw new RuntimeException("Factura no encontrada: " + facturaId);
        }
        Factura factura = facturaOpt.get();

        if (paymentAmount.compareTo(factura.getAmount()) != 0) {
            throw new RuntimeException("El monto del pago debe coincidir con el monto de la factura.");
        }

        Pago pago = new Pago();
        pago.setFactura(factura);
        pago.setPaymentAmount(paymentAmount);
        pago.setPaymentDate(new Date());
        pago.setStatus(State.PENDIENTE.name());
        factura.setStatus(State.EN_PROCESO.name());
        pagoRepository.save(pago);
        facturaRepository.save(factura);

        // Envío de notificación de aprobación/rechazo
        Long pagoId = pago.getPagoId();
        String asunto = "Notificación de pago pendiente " + factura.getInvoiceNumber();
        StringBuilder html = new StringBuilder();
        html.append("<h2>Pago Pendiente</h2>")
            .append("<p>Factura: ").append(factura.getInvoiceNumber()).append("</p>")
            .append("<p>Monto: $").append(paymentAmount).append("</p>")
            .append("<p>Fecha: ").append(pago.getPaymentDate()).append("</p>")
            .append("<a href=\"http://localhost:1818/finanzas/aprobar/")
            .append(pagoId).append("\" style=\"padding:8px 16px; background:#4CAF50; color:#fff; border-radius:4px; text-decoration:none;\">Aprobar Pago</a> ")
            .append("<a href=\"http://localhost:1818/finanzas/rechazar/")
            .append(pagoId).append("\" style=\"padding:8px 16px; background:#F00; color:#fff; border-radius:4px; text-decoration:none;\">Rechazar Pago</a>");
        emailService.sendEmail(emailFinanzas, asunto, html.toString());
        return pago;
    }

    @Override
    @Transactional
    public void aprobarPago(Long id) {
        Pago pago = pagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pago no encontrado: " + id));
        Factura factura = pago.getFactura();

        // Obtener presupuesto del mes de pago
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        PresupuestoCompras presupuesto = presupuestoComprasRepository
            .findByYearAndMonth(year, month)
            .orElseThrow(() -> new RuntimeException("Presupuesto no encontrado para " + year + "/" + month));

        BigDecimal pagoAmount = pago.getPaymentAmount();
        BigDecimal disponible = BigDecimal.valueOf(presupuesto.getRemainingAmount());
        if (disponible.compareTo(pagoAmount) < 0) {
            throw new RuntimeException("Presupuesto insuficiente. Disponible: " + disponible);
        }

        // Descarga el presupuesto y registra movimiento
        presupuesto.setRemainingAmount(presupuesto.getRemainingAmount() - pagoAmount.doubleValue());
        presupuesto.setUsedAmount(presupuesto.getUsedAmount() + pagoAmount.doubleValue());
        presupuestoComprasRepository.save(presupuesto);

        MovimientosPresupuesto mov = new MovimientosPresupuesto();
        mov.setPresupuestoCompras(presupuesto);
        mov.setDescription("Pago aprobado factura " + factura.getInvoiceNumber());
        mov.setAmount(pagoAmount.doubleValue());
        mov.setMovementType(State.GASTO);
        mov.setMovementDate(new Date());
        movimientosPresupuestoRepository.save(mov);

        // Actualizar estados
        pago.setStatus(State.APROBADA.name());
        factura.setStatus(State.PAGADA.name());
        pagoRepository.save(pago);
        facturaRepository.save(factura);
    }

    @Override
    @Transactional
    public void rechazarPago(Long id) {
        Pago pago = pagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pago no encontrado: " + id));
        pago.setStatus(State.RECHAZADA.name());
        pagoRepository.save(pago);
    }

    @Override
    public Iterable<Pago> getPagos() {
        return pagoRepository.findAll();
    }
}
