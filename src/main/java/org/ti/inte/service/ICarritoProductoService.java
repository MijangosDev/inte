package org.ti.inte.service;

import org.springframework.http.ResponseEntity;

public interface ICarritoProductoService
{
    public ResponseEntity<CarritoProductoResponseRest> crearCarrito(CarritoProducto carritoProducto);
    public ResponseEntity<CarritoProductoResponseRest> quitarCarrito(CarritoProducto carritoProducto);
    public ResponseEntity<CarritoProductoResponseRest> regresaCarrito();
}
