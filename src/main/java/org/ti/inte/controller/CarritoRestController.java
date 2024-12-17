package org.ti.inte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ti.inte.response.CarritoProductoResponseRest;
import org.ti.inte.service.ICarritoProductoService;

@RestController
@RequestMapping("/api/v1")
public class CarritoRestController {

    @Autowired
    private ICarritoProductoService service;

    @PostMapping("/carrito/eliminar")
    public ResponseEntity<CarritoProductoResponseRest> quitarDelCarrito(@RequestBody CarritoEliminarRequest carritoEliminarRequest) {
        // Llamamos al servicio para eliminar el producto del carrito
        ResponseEntity<CarritoProductoResponseRest> response = service.quitarCarrito(
                carritoEliminarRequest.getClienteId(),
                carritoEliminarRequest.getProductoId()
        );
        return response;
    }
}
