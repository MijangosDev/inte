package org.ti.inte.response;

public class ClienteResponseRest extends ResponseRest
{
    private ClienteResponse clienteResponse = new ClienteResponse();

    public ClienteResponse getClienteResponse() {
        return clienteResponse;
    }

    public void setClienteResponse(ClienteResponse clienteResponse) {
        this.clienteResponse = clienteResponse;
    }
}
