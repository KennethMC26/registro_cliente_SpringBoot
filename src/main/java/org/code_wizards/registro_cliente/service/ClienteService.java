package org.code_wizards.registro_cliente.service;

import org.code_wizards.registro_cliente.entity.Cliente;
import org.code_wizards.registro_cliente.repository.ClienteRepository;

//Inyectar dependencia
import org.springframework.beans.factory.annotation.Autowire;
//componente de SpringBoot parar crear aplicaciones
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService{
    @Override
    public List<Cliente> listarClientes() {
        return List.of();
    }

    @Override
    public Cliente buscarClienteporId(Integer codigo) {
        return null;
    }

    @Override
    public void guardarCliente(Cliente cliente) {

    }

    @Override
    public void eliminarCliente(Cliente cliente) {

    }
}
