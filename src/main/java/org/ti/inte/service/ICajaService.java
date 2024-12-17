package org.ti.inte.service;

import org.springframework.http.ResponseEntity;

public interface ICajaService
{
    public ResponseEntity<CajaResponseRest> nuevoCliente(Long id);
    public ResponseEntity<CajaResponseRest> atenderCliente();
    public ResponseEntity<CajaResponseRest> obtenerFila();
}
