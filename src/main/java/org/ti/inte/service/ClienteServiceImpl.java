package org.ti.inte.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ti.inte.model.Cliente;
import org.ti.inte.model.dao.IClienteDao;
import org.ti.inte.response.ClienteResponseRest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteService
{
    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private IClienteDao clienteDao;

    @Override
    @Transactional
    public ResponseEntity<ClienteResponseRest> crearCliente(Cliente cliente)
    {
        log.info("Creando Cliente");
        ClienteResponseRest response = new ClienteResponseRest();
        List<Cliente> list = new ArrayList<>();

        try
        {
            Cliente nuevoCliente = clienteDao.save(cliente);
            if (nuevoCliente != null)
            {
                list.add(nuevoCliente);
                response.getClienteResponse().setClientes(list);
                response.setMetadata("RESPUESTA OK","00","Cliente agregado correctamente");
            }else
            {
                log.warn("No se pudo crear el cliente");
                response.setMetadata("RESPUESTA OK","01","Cliente no agregado");
                return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e)
        {
            log.warn("¡Algo salió mal, intentalo nuevamente!");
            response.setMetadata("RESPUESTA ERROR","01","Respuesta fallida");
            e.printStackTrace();
            return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ClienteResponseRest>(response,HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<ClienteResponseRest> obtenerCliente(Long id) {
        log.info("bteniendo datos del Cliente");
        ClienteResponseRest response = new ClienteResponseRest();
        List<Cliente> list = new ArrayList<>();

        try
        {
            Optional<Cliente> cliente = clienteDao.findById(id);
            if (cliente.isPresent())
            {
                list.add(cliente.get());
                response.getClienteResponse().setClientes(list);
                response.setMetadata("Respuesta OK","00","Consulta exitosa");
            }
            else
            {
                log.warn("No se pudo obtener el cliente");
                response.setMetadata("Respuesta ERROR","01","Cliente no encontrado");
                return new ResponseEntity<ClienteResponseRest>(response,HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e)
        {
            log.warn("Error al obtener el cliente");
            response.setMetadata("RESPUESTA ERROR","01","Respuesta fallida");
            e.printStackTrace();
            return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ClienteResponseRest>(response,HttpStatus.OK);
    }
}
