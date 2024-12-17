package org.ti.inte.service;

import org.springframework.http.ResponseEntity;

public interface ISupermercadoService
{
    public ResponseEntity<CarritoProductoResponseRest> procesarCompra (Long id);
}
