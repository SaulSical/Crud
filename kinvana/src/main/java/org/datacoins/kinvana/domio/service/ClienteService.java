package org.datacoins.kinvana.domio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.datacoins.kinvana.persistence.crud.ClienteCrud;
import org.datacoins.kinvana.persistence.entity.Cliente;

import java.util.List;

@Service
public class ClienteService implements IClienteService{

    //Ingeyeccion de dependencia de mi repositorio (ClienteCrud) (ClienteRepositori)
    @Autowired
    private ClienteCrud crud;



    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = crud.findAll();
        return clientes;
    }

    @Override
    public Cliente buscarClienteProId(Integer codigo) {
        Cliente cliente = crud.findById(codigo).orElse(null );
        return cliente;
    }

    @Override
    public void gurdarCliente(Cliente cliente) {
        crud.save(cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        crud.delete(cliente);
    }
}
