package org.ti.inte.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ti.inte.model.CarritoProducto;
import org.ti.inte.model.Cliente;
import org.ti.inte.model.dao.ICarritoProductoDao;
import org.ti.inte.model.dao.IClienteDao;
import org.ti.inte.model.dao.IProductoDao;
import org.ti.inte.response.CarritoProductoResponseRest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupermercadoServiceImpl implements ISupermercadoService{
    private static final Logger log = LoggerFactory.getLogger(CarritoProductoServiceImpl.class);

    @Autowired
    private ICarritoProductoDao carritoProductoDao;

    @Autowired
    private IClienteDao clienteDaoDao;

    @Autowired
    private IProductoDao productoDao;

    @Override
    public ResponseEntity<CarritoProductoResponseRest> procesarCompra(Long id)
    {

        log.info("Procesando compra...");
        CarritoProductoResponseRest response = new CarritoProductoResponseRest();
        List<CarritoProductoResponseRest> list = new ArrayList<>();

        try
        {
            Optional<Cliente> clienteSaliente = clienteDaoDao.findById(id);
            if(clienteSaliente.isPresent()){
                Cliente aux = clienteSaliente.get();
                List<CarritoProducto> productos = aux.getCarrito();

                if (productos.isEmpty()){
                    log.warn("El carrito de "+aux.getNombre()+" no tiene productos");
                    response.setMetadata("RESPUESTA OK", "01", "Carrito vacío");
                    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
                }
                else
                {
                    float total = 0;

                    for (CarritoProducto p : productos){
                        total += p.getProducto().getPrecio() * p.getCantidad();
                        System.out.println(total);
                    }

                    carritoProductoDao.deleteAll(productos);
                    clienteDaoDao.deleteById(aux.getId());
                    response.getCarritoProductoResponse().setSubtotal(total);
                    response.setMetadata("RESPUESTA OK", "00", "El cliente ha pagado sus poductos correctamente");
                }
            }
            else
            {
                log.warn("No se encontró algún cliente con ese id");
                response.setMetadata("RESPUESTA OK", "01", "Cliente no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e)
        {
            log.info("¡Algo salió mal, intentalo nuevamente!");
            response.setMetadata("RESPUESTA ERROR","01","Respuesta fallida");
            e.printStackTrace();
            return new ResponseEntity<CarritoProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CarritoProductoResponseRest>(response, HttpStatus.CREATED);
    }
}