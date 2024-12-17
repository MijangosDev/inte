package org.ti.inte.service;

import org.springframework.http.ResponseEntity;
import org.ti.inte.model.Cliente;
import org.ti.inte.response.ClienteResponseRest;

public interface IClienteService
{
    public ResponseEntity<ClienteResponseRest> crearCliente(Cliente cliente);
    public ResponseEntity<ClienteResponseRest> obtenerCliente(Long id);
}
