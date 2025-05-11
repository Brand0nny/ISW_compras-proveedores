package com.isw.compras_proveedores.DTO;

public class EvaluacionCalidadDTO {
    private double score;
    private Long proveedorId;
    private String comments;
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public Long getProveedorId() {
        return proveedorId;
    }
    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    
}