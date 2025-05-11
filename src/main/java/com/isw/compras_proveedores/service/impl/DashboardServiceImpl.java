package com.isw.compras_proveedores.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isw.compras_proveedores.DTO.ResumenPresupuesto;
import com.isw.compras_proveedores.model.MovimientosPresupuesto;
import com.isw.compras_proveedores.model.PresupuestoCompras;
import com.isw.compras_proveedores.model.State;
import com.isw.compras_proveedores.repository.MovimientosPresupuestoRepository;
import com.isw.compras_proveedores.repository.PresupuestoComprasRepository;
import com.isw.compras_proveedores.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private PresupuestoComprasRepository budgetRepo;
    @Autowired
    private MovimientosPresupuestoRepository movRepo;

    @Override
    public ResumenPresupuesto getResumenPresupuesto(int year, int month) {
        PresupuestoCompras p = budgetRepo
           .findByYearAndMonth(year, month)
           .orElseThrow(() -> new RuntimeException("Presupuesto no encontrado para " + year + "/" + month));

        BigDecimal assigned = BigDecimal.valueOf(p.getAssignedAmount());
        BigDecimal remaining = BigDecimal.valueOf(p.getRemainingAmount());

        BigDecimal totalGasto = movRepo
           .sumAmountByYearAndMonthAndType(year, month, State.GASTO);
        BigDecimal spent = (totalGasto != null) ? totalGasto : BigDecimal.ZERO;

        return new ResumenPresupuesto(assigned, remaining, spent);
    }

    @Override
    public List<MovimientosPresupuesto> getMovimientos(int year, int month) {
        return movRepo
            .findAllByPresupuestoCompras_YearAndPresupuestoCompras_Month(year, month);
    }
}
