package org.ti.inte.service;

import org.springframework.http.ResponseEntity;
import org.ti.inte.model.Producto;
import org.ti.inte.response.ProductoResponseRest;

public interface IProductoService
{
    public ResponseEntity<ProductoResponseRest> insertarProducto(Producto producto);
}
