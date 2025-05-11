package com.isw.compras_proveedores.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isw.compras_proveedores.model.MovimientosPresupuesto;
import com.isw.compras_proveedores.model.State;

@Repository
public interface MovimientosPresupuestoRepository extends CrudRepository<MovimientosPresupuesto, Long> {
  
  // Devuelve todos los movimientos de un año/mes concreto
  List<MovimientosPresupuesto> findAllByPresupuestoCompras_YearAndPresupuestoCompras_Month(int year, int month);

  // Suma todos los montos de los movimientos de tipo GASTO de un año/mes
  @Query("SELECT SUM(m.amount) FROM MovimientosPresupuesto m "
       + "WHERE m.presupuestoCompras.year = :year "
       + "  AND m.presupuestoCompras.month = :month "
       + "  AND m.movementType = :type")
  BigDecimal sumAmountByYearAndMonthAndType(@Param("year") int year,
                                            @Param("month") int month,
                                            @Param("type") State type);
}
