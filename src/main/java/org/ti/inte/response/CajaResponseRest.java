package org.ti.inte.response;

import java.util.List;

public class CajaResponseRest extends ClienteResponseRest
{

    private List<Cliente> filaClientes;

    public List<Cliente> getFilaClientes() {
        return filaClientes;
    }

    public void setFilaClientes(List<Cliente> filaClientes) {
        this.filaClientes = filaClientes;
    }
}
