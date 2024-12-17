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
public class ProductoRestController
{
    @Autowired
    private IProductoService service;
    @Autowired
    private RestClient.Builder restClientBuilder;

    @PostMapping("/producto/agregar")
    public ResponseEntity<ProductoResponseRest> agregarProducto(@RequestBody Producto producto)
    {
        ResponseEntity<ProductoResponseRest> response = service.insertarProducto(producto);
        return response;
    }
}
