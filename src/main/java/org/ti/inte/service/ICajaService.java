package org.ti.inte.service;

import org.springframework.http.ResponseEntity;
import org.ti.inte.response.CajaResponseRest;

public interface ICajaService
{
    public ResponseEntity<CajaResponseRest> nuevoCliente(Long id);
    public ResponseEntity<CajaResponseRest> atenderCliente();
    public ResponseEntity<CajaResponseRest> obtenerFila();
}
