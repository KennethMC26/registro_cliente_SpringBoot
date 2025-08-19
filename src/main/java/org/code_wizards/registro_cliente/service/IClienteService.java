package org.code_wizards.registro_cliente.service;

import org.code_wizards.registro_cliente.entity.Cliente;
import java.util.List;

public interface IClienteService {
    public List<Cliente> listarClientes();
    public Cliente buscarClienteporId(Integer codigo);
    public void guardarCliente(Cliente cliente);//agregar y actualizar
    public void eliminarCliente(Cliente cliente);
}
