package org.datacoins.kinvana.domio.service;


import org.datacoins.kinvana.persistence.entity.Cliente;

import java.util.List;

public interface IClienteService {

     List<Cliente> listarClientes();
     Cliente buscarClienteProId(Integer codigo);
     void gurdarCliente(Cliente cliente);
     void eliminarCliente(Cliente cliente);
}
