package com.isw.compras_proveedores.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name="detallerequisicion")
public class DetalleRequisicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long detalleRequisicionId;
    @NotNull
    private Integer quantity;

    //relaciones
    @ManyToOne(optional=false)
    @JoinColumn(name="FK_requisicion_id")
    @JsonBackReference
    private Requisicion requisicion;
    
    
    @ManyToOne(optional=false)
    @JoinColumn(name="FK_material_id")    
    private Material material;

    public Long getDetalleRequisicionId() {
        return detalleRequisicionId;
    }

    public void setDetalleRequisicionId(Long detalleRequisicionId) {
        this.detalleRequisicionId = detalleRequisicionId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Requisicion getRequisicion() {
        return requisicion;
    }

    public void setRequisicion(Requisicion requisicion) {
        this.requisicion = requisicion;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
