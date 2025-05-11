package com.isw.compras_proveedores.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResumenPresupuesto {
    private BigDecimal assigned;
    private BigDecimal remaining;
    private BigDecimal spent;
    
    public BigDecimal getAssigned() {
        return assigned;
    }
    public void setAssigned(BigDecimal assigned) {
        this.assigned = assigned;
    }
    public BigDecimal getRemaining() {
        return remaining;
    }
    public void setRemaining(BigDecimal remaining) {
        this.remaining = remaining;
    }
    public BigDecimal getSpent() {
        return spent;
    }
    public void setSpent(BigDecimal spent) {
        this.spent = spent;
    }
}
