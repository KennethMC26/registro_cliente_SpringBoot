package org.code_wizards.registro_cliente.controller;

import jakarta.annotation.PostConstruct;
//import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.code_wizards.registro_cliente.entity.Cliente;
import org.code_wizards.registro_cliente.service.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


//Componente generico
@Component
//Alcance de tipo VIEW
//@ViewScoped
//Getter y Setter de Lombok
@Data
public class IndexController {

    @Autowired
    IClienteService clienteService;
    private List<Cliente> clientes;
    private Cliente clienteSeleccionado;
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void cargarDatos(){
        this.clientes = this.clienteService.listarClientes();
        this.clientes.forEach(cliente -> logger.info(cliente.toString()));
    }

    public void agregarCliente(){
        this.clienteSeleccionado=new Cliente();
    }
}
