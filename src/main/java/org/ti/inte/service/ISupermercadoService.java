package org.ti.inte.service;

import org.springframework.http.ResponseEntity;
import org.ti.inte.response.CarritoProductoResponseRest;

public interface ISupermercadoService
{
    public ResponseEntity<CarritoProductoResponseRest> procesarCompra (Long id);
}
