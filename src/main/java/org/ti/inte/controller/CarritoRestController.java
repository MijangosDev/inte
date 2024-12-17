package org.ti.inte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api/v1")
public class CarritoRestController
{
    @Autowired
    private ICarritoProductoService service;
    @Autowired
    private RestClient.Builder restClientBuilder;

    @PostMapping("/carrito/agregar")
    public ResponseEntity<CarritoProductoResponseRest> crearCarrito(@RequestBody CarritoProducto carritoProducto){
        ResponseEntity<CarritoProductoResponseRest> response = service.crearCarrito(carritoProducto);
        return response;
    }

    @PostMapping("/carrito/eliminar")
    public ResponseEntity<CarritoProductoResponseRest> quitarDelCarrito(@RequestBody CarritoProducto carritoProducto){
        ResponseEntity<CarritoProductoResponseRest> response = service.quitarCarrito(carritoProducto);
        return response;
    }

    @PostMapping("/carrito/deshacer")
    public ResponseEntity<CarritoProductoResponseRest> regresarAlCarrito(){
        ResponseEntity<CarritoProductoResponseRest> response = service.regresaCarrito();
        return response;
    }
}
