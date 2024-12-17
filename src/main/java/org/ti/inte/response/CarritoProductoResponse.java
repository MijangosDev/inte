package org.ti.inte.response;

import java.util.List;

public class CarritoProductoResponse
{


    private float subtotal;

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    private List<CarritoProducto> carritoProducto;

    public List<CarritoProducto> getCarritoProducto(){
        return carritoProducto;
    }

    public void setCarritoProducto(List<CarritoProducto> carritoProducto){
        this.carritoProducto = carritoProducto;
    }
}
