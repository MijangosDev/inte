package org.ti.inte.service;

import org.springframework.http.ResponseEntity;

public interface IClienteService
{
    public ResponseEntity<ClienteResponseRest> crearCliente(Cliente cliente);
    public ResponseEntity<ClienteResponseRest> obtenerCliente(Long id);
}
