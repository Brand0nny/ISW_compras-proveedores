package com.isw.compras_proveedores.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movimientos_presupuesto")
public class MovimientosPresupuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movement")
    private Long idMovement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_budget", nullable = false)
    private PresupuestoCompras presupuestoCompras;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "amount", nullable = false)
    private double amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type", nullable = false)
    private State movementType;
    @Column(name = "movement_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date movementDate;

    // Getters and setters
    public Long getIdMovement() {
        return idMovement;
    }

    public void setIdMovement(Long idMovement) {
        this.idMovement = idMovement;
    }

    public PresupuestoCompras getPresupuestoCompras() {
        return presupuestoCompras;
    }

    public void setPresupuestoCompras(PresupuestoCompras presupuestoCompras) {
        this.presupuestoCompras = presupuestoCompras;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public Date getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(Date movementDate) {
        this.movementDate = movementDate;
    }

    public State getMovementType() {
        return movementType;
    }

    public void setMovementType(State movementType) {
        this.movementType = movementType;
    }

}
