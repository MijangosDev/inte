package org.ti.inte.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ti.inte.data.CustomQueue;
import org.ti.inte.model.Cliente;
import org.ti.inte.model.dao.IClienteDao;
import org.ti.inte.response.CajaResponseRest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CajaServiceImpl implements ICajaService
{
    @Autowired
    private IClienteDao clienteDao;

    private static final Logger log = LoggerFactory.getLogger(CajaServiceImpl.class);

    private final CustomQueue<Cliente> fila = new CustomQueue<Cliente>(10);

    @Override
    @Transactional
    public ResponseEntity<CajaResponseRest> nuevoCliente(Long id)
    {
        log.info("Un nuevo cliente se ha unido a la fila");
        CajaResponseRest response = new CajaResponseRest();
        List<Cliente> list = new ArrayList<>();
        response.setFilaClientes(fila.getElements());

        try
        {
            Optional<Cliente> aux = clienteDao.findById(id);

            if(aux.isPresent()) {
                if (!fila.offer(aux.get())) {
                    response.setFilaClientes(fila.getElements());
                    response.setMetadata("RESPUESTA OK", "00", "Cliente se unió a la fila correctamente");
                }
                log.warn("No se pudo agregar el cliente a la fila");
                response.setMetadata("RESPUESTA OK", "01", "Cliente no formado");
                response.setFilaClientes(fila.getElements());

            }else{
                log.warn("No se algún cliente con ese id");
                response.setMetadata("RESPUESTA OK", "01", "Cliente no encontrado");
                response.setFilaClientes(fila.getElements());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e)
        {
            log.warn("¡Algo salió mal, intentalo nuevamente!");
            response.setMetadata("RESPUESTA ERROR","01","Respuesta fallida");
            e.printStackTrace();
            return new ResponseEntity<CajaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CajaResponseRest>(response,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CajaResponseRest> atenderCliente()
    {
        log.info("Atendiendo clientes");
        CajaResponseRest response = new CajaResponseRest();
        List<Cliente> list = new ArrayList<>();
        response.setFilaClientes(fila.getElements());

        try{
            if(fila.isEmpty())
            {
                log.warn("No hay clientes en la fila");
                response.setMetadata("RESPUESTA OK","01","Fila vacía");
                return new ResponseEntity<CajaResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            Cliente atendido = fila.poll();

            if (atendido == null)
            {
                response.setFilaClientes(fila.getElements());
                log.warn("No se pudo sacar el cliente a la fila");
                response.setMetadata("RESPUESTA OK","01","Cliente no expulsado de la fila");
                return new ResponseEntity<CajaResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

            response.setFilaClientes(fila.getElements());
            response.setMetadata("RESPUESTA OK", "00", "Cliente abandonó la fila correctamente");

        }
        catch (Exception e)
        {
            log.warn("¡Algo salió mal, intentalo nuevamente!");
            response.setMetadata("RESPUESTA ERROR","01","Respuesta fallida");
            e.printStackTrace();
            return new ResponseEntity<CajaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CajaResponseRest>(response,HttpStatus.OK);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public ResponseEntity<CajaResponseRest> obtenerFila()
    {
        log.info("Obteniendo a los clientes en la fila");
        CajaResponseRest response = new CajaResponseRest();
        List<Cliente> clientes = new ArrayList<>();
        response.setFilaClientes(fila.getElements());

        try
        {
            if(fila.isEmpty())
            {
                log.info("No hay clientes en la fila");
                response.setMetadata("RESPUESTA OK","01","Fila vacía");
                response.setFilaClientes(fila.getElements());
                return new ResponseEntity<CajaResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            clientes = fila.getElements();

            if (clientes == null)
            {
                log.warn("No se pudo obtener la fila de clientes");
                response.setMetadata("RESPUESTA OK","01","Fila de clientes no obtenida");
                return new ResponseEntity<CajaResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

            response.getClienteResponse().setClientes(Collections.singletonList((Cliente) clientes));
            response.setFilaClientes(fila.getElements());
            response.setMetadata("RESPUESTA OK","00","Fila de clientes obtenida exitosamente");

        }
        catch (Exception e)
        {
            log.warn("¡Algo salió mal, intentalo nuevamente!");
            response.setMetadata("RESPUESTA ERROR","01","Respuesta fallida");
            e.printStackTrace();
            return new ResponseEntity<CajaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CajaResponseRest>(response, HttpStatus.OK);
    }
}