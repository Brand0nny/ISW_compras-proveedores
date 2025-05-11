package com.isw.compras_proveedores.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isw.compras_proveedores.model.Reporte;
import com.isw.compras_proveedores.repository.ReporteRepository;
import com.isw.compras_proveedores.service.ReporteService;
@Service
public class ReporteServiceImpl implements ReporteService {
    @Autowired
    ReporteRepository reporteRepository;


    @Override
    public Reporte getReporteById(Long reporteId) {
        System.out.println(reporteId);
        return reporteRepository.findById(reporteId).get();
    }

    @Override
    public Iterable<Reporte> getReportes() {
        return reporteRepository.findAll();
    }

}
