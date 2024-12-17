package org.ti.inte.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.ti.inte.model.CarritoProducto;

import java.util.List;

public interface ICarritoProductoDao extends JpaRepository<CarritoProducto, Long> {
    @Query("select c from CarritoProducto c where Cliente = :id")
    public List<CarritoProducto> findByClienteId(@Param("id") Long id);
    void deleteByCliente_IdAndProducto_Id(Long clienteId, Long productoId);

}

