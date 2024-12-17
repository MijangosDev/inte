package org.ti.inte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.ti.inte.model.Cliente;
import org.ti.inte.response.ClienteResponseRest;
import org.ti.inte.service.IClienteService;

@RestController
@RequestMapping("/api/v1")
public class ClienteRestController
{
    @Autowired
    private IClienteService service;
    @Autowired
    private RestClient.Builder restClientBuilder;

    @PostMapping("/cliente/agregar")
    public ResponseEntity<ClienteResponseRest> agregarCliente(@RequestBody Cliente cliente)
    {
        ResponseEntity<ClienteResponseRest> response = service.crearCliente(cliente);
        return response;
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<ClienteResponseRest> obtenerCliente(@PathVariable Long id)
    {
        ResponseEntity<ClienteResponseRest> response = service.obtenerCliente(id);
        return response;
    }
}
