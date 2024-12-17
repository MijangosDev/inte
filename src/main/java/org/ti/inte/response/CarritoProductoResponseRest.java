package org.ti.inte.response;

public class CarritoProductoResponseRest extends ResponseRest
{
    private CarritoProductoResponse carritoResponse = new CarritoProductoResponse();

    public CarritoProductoResponse getCarritoProductoResponse(){
        return carritoResponse;
    }

    public void setCarritoResponse(CarritoProductoResponse carritoResponse){
        this.carritoResponse = carritoResponse;
    }

}
