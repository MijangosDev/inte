package org.ti.inte.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoProductoServiceImpl implements ICarritoProductoService {
    private static final Logger log = LoggerFactory.getLogger(CarritoProductoServiceImpl.class);

    @Autowired
    private ICarritoProductoDao carritoProductoDao;

    @Autowired
    private IClienteDao clienteDao;

    private final CustomStack<CarritoProducto> productos = new CustomStack<>(10);

    HashMap<Long,CustomStack<CarritoProducto>> produ = new HashMap<>();

    @Override
    @Transactional
    public ResponseEntity<CarritoProductoResponseRest> crearCarrito(CarritoProducto carritoProducto) {
        log.info("Creando carrito de productos");
        CarritoProductoResponseRest response = new CarritoProductoResponseRest();
        List<CarritoProducto> list = new ArrayList<>();

        try {
            System.out.println(carritoProducto.getProducto().getNombre());
            carritoProducto.setId(null);
            CarritoProducto nuevoCarritoProducto = carritoProductoDao.save(carritoProducto);

            if (nuevoCarritoProducto.getProducto() != null) {
                list.add(nuevoCarritoProducto);
                response.getCarritoProductoResponse().setCarritoProducto(list);
                response.setMetadata("RESPUESTA OK", "00", "Carrito de productos creado correctamente");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                log.warn("El carrito creado no tiene un producto asociado");
                response.setMetadata("RESPUESTA ERROR", "01", "El carrito creado no tiene un producto asociado");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("Error al crear el carrito de productos", e);
            response.setMetadata("RESPUESTA ERROR", "01", "Error al crear el carrito de productos");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    @Transactional
    public ResponseEntity<CarritoProductoResponseRest> quitarCarrito(CarritoProducto carritoProducto) {
        log.info("Creando carrito de productos");
        CarritoProductoResponseRest response = new CarritoProductoResponseRest();
        List<CarritoProducto> list = new ArrayList<>();

        try{
            Optional<CarritoProducto> carrito = carritoProductoDao.findById(carritoProducto.getId());
            if(carrito.isPresent())
            {
                productos.push(carrito.get());
                carritoProductoDao.delete(carrito.get());

                produ.put(carritoProducto.getCliente().getId(),productos);

                System.out.println(productos.peek().getCliente().getNombre()+" Se guarda");

                list.add(carritoProducto);

                response.getCarritoProductoResponse().setCarritoProducto(productos.getItems());
                response.setMetadata("RESPUESTA OK", "00", "Producto "+productos.peek()+" removido exitosamente");
            }
            else
            {
                log.warn("No se algún cliente con ese id");
                response.setMetadata("RESPUESTA OK", "01", "Cliente no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.warn("¡Algo salió mal, intentalo nuevamente!");
            response.setMetadata("RESPUESTA ERROR", "01", "No respuesta fallida");
            e.printStackTrace();
            return new ResponseEntity<CarritoProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CarritoProductoResponseRest>(response, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<CarritoProductoResponseRest> regresaCarrito() {
        log.info("Regresando el producto al carrito");
        CarritoProductoResponseRest response = new CarritoProductoResponseRest();
        List<CarritoProducto> list = new ArrayList<>();

        try {
            //Optional<Cliente> cliente = clienteDao.findById(id);

            System.out.println(productos.peek().getCliente().getNombre());

            //if (cliente.isPresent()) {

            CarritoProducto carrito = productos.pop();

            if (!(carrito == null))
            {
                carrito.setId(null);
                carritoProductoDao.save(carrito); // Guardar producto en la base de datos
                list.add(carrito);
                response.getCarritoProductoResponse().setCarritoProducto(list);
                response.setMetadata("RESPUESTA OK", "00", "Producto retornado con éxito");
            } else {
                log.warn("El carrito está vacío ");
                response.setMetadata("RESPUESTA ERROR", "01", "No hay productos para retornar");
                return new ResponseEntity<CarritoProductoResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
            /*} else {
                log.warn("Cliente no encontrado");
                response.setMetadata("RESPUESTA ERROR", "01", "Cliente no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
             }*/
        } catch (Exception e) {
            log.error("¡Algo salió mal, intentalo nuevamente!", e);
            response.setMetadata("RESPUESTA ERROR", "01", "Error al retornar el producto");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}