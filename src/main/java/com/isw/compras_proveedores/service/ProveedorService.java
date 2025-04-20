package com.isw.compras_proveedores.service;

import com.isw.compras_proveedores.model.Proveedor;

public interface ProveedorService {
    public Boolean registerProveedor(Proveedor proveedor);
    public Boolean deleteProveedor(Long proveedorId);
    public Proveedor getProveedor (Long proveedorId);
    public Iterable<Proveedor> getProveedores();
    public Proveedor updateProveedor(Long id, Proveedor proveedor);
}
