package org.ti.inte.response;

import org.ti.inte.model.Cliente;

import java.util.List;

public class ClienteResponse
{
    private List<Cliente> clientes;

    public List<Cliente> getClientes()
    {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes)
    {
        this.clientes = clientes;
    }
}
