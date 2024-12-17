package org.ti.inte.service;

import org.springframework.http.ResponseEntity;

public interface IProductoService
{
    public ResponseEntity<ProductoResponseRest> insertarProducto(Producto producto);
}
