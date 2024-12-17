package org.ti.inte.service;

import org.springframework.http.ResponseEntity;
import org.ti.inte.model.CarritoProducto;
import org.ti.inte.response.CarritoProductoResponseRest;

public interface ICarritoProductoService
{
    public ResponseEntity<CarritoProductoResponseRest> crearCarrito(CarritoProducto carritoProducto);
    //public ResponseEntity<CarritoProductoResponseRest> quitarCarrito(CarritoProducto carritoProducto);
    public ResponseEntity<CarritoProductoResponseRest> regresaCarrito();

    ResponseEntity<CarritoProductoResponseRest> quitarCarrito(Long clienteId, Long productoId);
}
