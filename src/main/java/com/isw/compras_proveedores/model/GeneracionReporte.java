package com.isw.compras_proveedores.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class GeneracionReporte {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="id")
private Long generarionReporteId;

@Column(name = "generationDate")
private Date generationDate;

@Column(name = "reportType")
private String reportType;

@Column(name = "parameters", columnDefinition = "TEXT")
private String parameters;

@ManyToOne
@JoinColumn(name = "FK_usuario_id")
private Usuario usuario;

@ManyToOne
@JoinColumn(name = "FK_reporte_id")
private Reporte reporte;

    public Long getGenerarionReporteId() {
        return generarionReporteId;
    }

    public void setGenerarionReporteId(Long generarionReporteId) {
        this.generarionReporteId = generarionReporteId;
    }

    public Date getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(Date generationDate) {
        this.generationDate = generationDate;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

}
