package org.ti.inte.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ti.inte.model.Producto;
import org.ti.inte.model.dao.IProductoDao;
import org.ti.inte.response.ProductoResponseRest;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService
{
    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private IProductoDao productoDao;

    @Override
    public ResponseEntity<ProductoResponseRest> insertarProducto(Producto producto)
    {
        log.info("Creando Producto");
        ProductoResponseRest response = new ProductoResponseRest();
        List<Producto> list = new ArrayList<>();

        try
        {
            Producto nuevoProducto =    productoDao.save(producto);
            if (nuevoProducto != null)
            {
                list.add(nuevoProducto);
                response.getProductoResponse().setProductos(list);
                response.setMetadata("RESPUESTA OK","00","Producto agregado correctamente");
            }else
            {
                log.warn("No se pudo registrar el producto");
                response.setMetadata("RESPUESTA OK","01","Producto no registrado");
                return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e)
        {
            log.warn("¡Algo salió mal, intentalo nuevamente!");
            response.setMetadata("RESPUESTA ERROR","01","Respuesta fallida");
            e.printStackTrace();
            return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductoResponseRest>(response,HttpStatus.CREATED);
    }
}
