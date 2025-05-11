package com.isw.compras_proveedores.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "presupuesto_compras")
public class PresupuestoCompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presupuesto")
    private Long idPresupuesto;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "month", nullable = false)
    private int month;

    @Column(name = "assigned_amount", nullable = false)
    private double assignedAmount;

    @Column(name = "remaining_amount", nullable = false)
    private double remainingAmount;

    @Column(name = "used_amount", nullable = false, columnDefinition = "DECIMAL(12,2) DEFAULT 0")
    private double usedAmount;

    @Column(name = "accumulated_previous", columnDefinition = "DECIMAL(12,2) DEFAULT 0")
    private double accumulatedPrevious;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private State status;

    @Column(name = "creation_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "update_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    // Getters and setters
    public Long getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(Long idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getAssignedAmount() {
        return assignedAmount;
    }

    public void setAssignedAmount(double assignedAmount) {
        this.assignedAmount = assignedAmount;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public double getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(double usedAmount) {
        this.usedAmount = usedAmount;
    }

    public double getAccumulatedPrevious() {
        return accumulatedPrevious;
    }

    public void setAccumulatedPrevious(double accumulatedPrevious) {
        this.accumulatedPrevious = accumulatedPrevious;
    }

 

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public State getStatus() {
        return status;
    }

    public void setStatus(State status) {
        this.status = status;
    }

   
}

