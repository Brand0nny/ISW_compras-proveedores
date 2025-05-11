package com.isw.compras_proveedores.service;

import com.isw.compras_proveedores.model.OrdenCompra;
import com.isw.compras_proveedores.model.Requisicion;
import java.util.List;

public interface RequisicionService {

    public Requisicion registerRequisicion(Requisicion requisicion);
    public Boolean deleteRequisicion(Long requisicionId);
    public Requisicion getRequisicion(Long requisicionId);
    public Iterable<Requisicion> getRequisiciones();
    public Requisicion updateRequisicion(Long id, Requisicion requisicion);
    public Iterable<Requisicion> getRequisicionesPendientes();
    public OrdenCompra aproveOrdenRequisicion(Long id, Long proveedor, String user);
    public List<Requisicion> getLatestRequiredRequisiciones();
    public List<Requisicion> getTop5LatestRequiredRequisiciones();
    public List<Requisicion> getLatestRequestRequisiciones();
    public List<Requisicion> getTop5LatestRequestRequisiciones();
    public Long countByStatus(String status);
    public Iterable<Requisicion> getRequisicionesPorEstado(String status);
    public Requisicion setStateOrdenRequisicion(Long id, String status);
    public List<Requisicion> getRequisicionesDesc(); 
    public List<Requisicion> getRequisicionesStatusDesc(String status); 


}
