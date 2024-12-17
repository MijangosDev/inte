package org.ti.inte.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICarritoProductoDao extends CrudRepository<CarritoProducto, Long> {
    @Query("select c from CarritoProducto c where Cliente = :id")
    public List<CarritoProducto> findByClienteId(@Param("id") Long id);
}

