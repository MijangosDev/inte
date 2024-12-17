package org.ti.inte.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.ti.inte.model.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long> { }