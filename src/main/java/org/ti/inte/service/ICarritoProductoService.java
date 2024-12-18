package org.ti.inte.service;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.ti.inte.model.CarritoProducto;
import org.ti.inte.response.CarritoProductoResponseRest;

public interface ICarritoProductoService {

    ResponseEntity<CarritoProductoResponseRest> crearCarrito(CarritoProducto carritoProducto);

    ResponseEntity<CarritoProductoResponseRest> regresaCarrito();

    ResponseEntity<CarritoProductoResponseRest> quitarCarrito(Long clienteId, Long productoId);
}
