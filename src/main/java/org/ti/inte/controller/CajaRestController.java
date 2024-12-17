package org.ti.inte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.ti.inte.response.CajaResponseRest;
import org.ti.inte.service.ICajaService;

@RestController
@RequestMapping("api/v1")
public class CajaRestController
{
    @Autowired
    private ICajaService service;
    @Autowired
    private RestClient.Builder restClientBuilder;

    @GetMapping("/caja/obtenerFila")
    public ResponseEntity<CajaResponseRest> obtenerFila(){
        ResponseEntity<CajaResponseRest> response = service.obtenerFila();
        return response;
    }

    @PostMapping("/caja/agregar/{id}")
    public ResponseEntity<CajaResponseRest> agregarALafila(@PathVariable Long id) {
        ResponseEntity<CajaResponseRest> response = service.nuevoCliente(id);
        return response;
    }

    @PostMapping("/caja/atender")
    public ResponseEntity<CajaResponseRest> salirDeLafila(){
        ResponseEntity<CajaResponseRest> response = service.atenderCliente();
        return response;
    }
}
