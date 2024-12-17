package org.ti.inte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.ti.inte.response.CarritoProductoResponseRest;
import org.ti.inte.service.ISupermercadoService;

@RestController
@RequestMapping("api/v1")
public class SupermercadoRestController
{
    @Autowired
    private ISupermercadoService service;
    @Autowired
    private RestClient.Builder restClientBuilder;

    @PostMapping("/supermercado/comprar/{id}")
    public ResponseEntity<CarritoProductoResponseRest> salirDeLafila(@PathVariable Long id){
        ResponseEntity<CarritoProductoResponseRest> response = service.procesarCompra(id);
        return response;
    }
}
