package com.isw.compras_proveedores.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name="requisicion")
public class Requisicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long requisicionId;
    @NotBlank(message = "No puede estar en blanco")
    private String description;
    private String motive;
    private String status;
    @Temporal(TemporalType.DATE)
    @Column(name = "requestDate")
    private Date requestDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "requiredDate")
    private Date requiredDate;
    @NotBlank(message = "No puede estar en blanco")
    @Column(name = "centerCost")
    private String centerCost;
    
    @OneToMany(
      mappedBy = "requisicion",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<DetalleRequisicion> detalles;



    public Long getRequisicionId() {
        return requisicionId;
    }

    public void setRequisicionId(Long requisicionId) {
        this.requisicionId = requisicionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public String getCenterCost() {
        return centerCost;
    }

    public void setCenterCost(String centerCost) {
        this.centerCost = centerCost;
    }

    public List<DetalleRequisicion> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleRequisicion> detalles) {
        this.detalles = detalles;
    }


}
