package com.isw.compras_proveedores.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name="material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long materialId;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private Double cost;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Producto> productos;
    
    @JsonIgnore
    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
    private List<DetalleRequisicion> detallesRequisicion;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<DetalleRequisicion> getDetallesRequisicion() {
        return detallesRequisicion;
    }

    public void setDetallesRequisicion(List<DetalleRequisicion> detallesRequisicion) {
        this.detallesRequisicion = detallesRequisicion;
    }
}
