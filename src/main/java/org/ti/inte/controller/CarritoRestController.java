package org.ti.inte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ti.inte.model.CarritoProducto;
import org.ti.inte.response.CarritoProductoResponseRest;
import org.ti.inte.service.ICarritoProductoService;

@RestController
@RequestMapping("/super")
public class CarritoRestController {

    @Autowired
    private ICarritoProductoService service;

    @PostMapping("/carrito/crear")
    public ResponseEntity<CarritoProductoResponseRest> crearCarrito(@RequestBody CarritoProducto carritoProducto) {
        ResponseEntity<CarritoProductoResponseRest> response = service.crearCarrito(carritoProducto);
        return response;
    }

    @PostMapping("/carrito/regresar")
    public ResponseEntity<CarritoProductoResponseRest> regresarCarrito() {
        ResponseEntity<CarritoProductoResponseRest> response = service.regresaCarrito();
        return response;
    }

    @PostMapping("/carrito/eliminar")
    public ResponseEntity<CarritoProductoResponseRest> quitarDelCarrito(@RequestBody CarritoEliminarRequest carritoEliminarRequest) {
        ResponseEntity<CarritoProductoResponseRest> response = service.quitarCarrito(
                carritoEliminarRequest.getClienteId(),
                carritoEliminarRequest.getProductoId()
        );
        return response;
    }
}
