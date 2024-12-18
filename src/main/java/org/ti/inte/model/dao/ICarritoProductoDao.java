package org.ti.inte.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.ti.inte.model.CarritoProducto;

import java.util.List;

public interface ICarritoProductoDao extends JpaRepository<CarritoProducto, Long> {
}

