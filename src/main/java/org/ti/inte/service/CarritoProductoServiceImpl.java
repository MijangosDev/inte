package org.ti.inte.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ti.inte.data.CustomStack;
import org.ti.inte.model.CarritoProducto;
import org.ti.inte.model.dao.ICarritoProductoDao;
import org.ti.inte.model.dao.IClienteDao;
import org.ti.inte.response.CarritoProductoResponseRest;

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
    HashMap<Long, CustomStack<CarritoProducto>> produ = new HashMap<>();

    // Método común para construir las respuestas
    private ResponseEntity<CarritoProductoResponseRest> buildResponse(String mensaje, String codigo, String detalle, HttpStatus status) {
        CarritoProductoResponseRest response = new CarritoProductoResponseRest();
        response.setMetadata(mensaje, codigo, detalle);
        return new ResponseEntity<>(response, status);
    }

    @Override
    public ResponseEntity<CarritoProductoResponseRest> crearCarrito(CarritoProducto carritoProducto) {
        log.info("Creando carrito de productos");

        try {
            if (carritoProducto.getProducto() == null) {
                log.warn("El carrito no tiene un producto asociado");
                return buildResponse("RESPUESTA ERROR", "01", "El carrito no tiene un producto asociado", HttpStatus.BAD_REQUEST);
            }

            carritoProducto.setId(null);
            CarritoProducto nuevoCarritoProducto = carritoProductoDao.save(carritoProducto);

            List<CarritoProducto> list = new ArrayList<>();
            list.add(nuevoCarritoProducto);

            return buildResponse("RESPUESTA OK", "00", "Carrito de productos creado correctamente", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error al crear el carrito de productos", e);
            return buildResponse("RESPUESTA ERROR", "01", "Error al crear el carrito de productos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<CarritoProductoResponseRest> regresaCarrito() {
        log.info("Regresando el producto al carrito");

        try {
            CarritoProducto carrito = productos.pop(); // Recuperamos el último producto eliminado

            if (carrito == null) {
                log.warn("El carrito está vacío");
                return buildResponse("RESPUESTA ERROR", "01", "No hay productos para retornar", HttpStatus.BAD_REQUEST);
            }

            carrito.setId(null); // Asegurar que sea una nueva instancia para la base de datos
            carritoProductoDao.save(carrito); // Guardar producto en la base de datos

            List<CarritoProducto> list = new ArrayList<>();
            list.add(carrito);

            // Respuesta exitosa
            CarritoProductoResponseRest response = new CarritoProductoResponseRest();
            response.getCarritoProductoResponse().setCarritoProducto(list);
            response.setMetadata("RESPUESTA OK", "00", "Producto retornado al carrito con éxito");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("¡Algo salió mal al regresar el producto al carrito!", e);
            return buildResponse("RESPUESTA ERROR", "01", "Error al retornar el producto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<CarritoProductoResponseRest> quitarCarrito(Long clienteId, Long productoId) {
        log.info("Eliminando producto con ID {} del carrito del cliente con ID {}", productoId, clienteId);

        try {
            // Buscar el carrito de productos del cliente
            Optional<CarritoProducto> carritoProductoOptional = carritoProductoDao.findById(productoId);

            // Si el carrito de productos existe, proceder a la eliminación
            if (carritoProductoOptional.isPresent()) {
                CarritoProducto carritoProducto = carritoProductoOptional.get();

                // Verificar que el producto pertenece al cliente correcto
                if (carritoProducto.getCliente().getId().equals(clienteId)) {
                    // Eliminar el producto del carrito
                    carritoProductoDao.delete(carritoProducto);

                    // Agregar el producto eliminado a la pila de productos eliminados (historial)
                    productos.push(carritoProducto);

                    // Guardar el estado de la pila de productos por cliente
                    produ.put(clienteId, productos);

                    // Respuesta exitosa
                    CarritoProductoResponseRest response = new CarritoProductoResponseRest();
                    response.getCarritoProductoResponse().setCarritoProducto(productos.getItems());
                    response.setMetadata("RESPUESTA OK", "00", "Producto eliminado exitosamente del carrito");

                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    log.warn("El producto con ID {} no pertenece al cliente con ID {}", productoId, clienteId);
                    return buildResponse("RESPUESTA ERROR", "02", "El producto no pertenece al cliente especificado", HttpStatus.BAD_REQUEST);
                }
            } else {
                log.warn("Producto con ID {} no encontrado", productoId);
                return buildResponse("RESPUESTA ERROR", "01", "Producto no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error al eliminar el producto del carrito", e);
            return buildResponse("RESPUESTA ERROR", "03", "Error al eliminar el producto del carrito", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
