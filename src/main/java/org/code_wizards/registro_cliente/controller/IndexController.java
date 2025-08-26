package org.code_wizards.registro_cliente.controller;

import jakarta.annotation.PostConstruct;
//import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.apache.logging.log4j.message.Message;
import org.code_wizards.registro_cliente.entity.Cliente;
import org.code_wizards.registro_cliente.service.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.jsf.FacesContextUtils;

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

    public void guardarCliente(){
        logger.info("Cliente a guardar: " + this.clienteSeleccionado);
        //agregar
        if (this.clienteSeleccionado.getCodigoCliente()==null){
            this.clienteService.guardarCliente(this.clienteSeleccionado);
            this.clientes.add(this.clienteSeleccionado);
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente agregado"));
        }
        //modificar
        else{
            this.clienteService.guardarCliente(this.clienteSeleccionado);
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente actualizado"));
        }
        //ocultar la ventada modal
        PrimeFaces.current().executeScript("PF('ventanaModalCliente).hide");
        //Actualizar tabla utilizando tecnologia incorporada - AJAx -
        PrimeFaces.current().ajax.update("formulario-clientes:mensaje-emergente","formulario-clientes:tabla-clientes");
        //limpiar el objero cliente seleccionado
        this.clienteSeleccionado = null;
    }

    public void eliminarCliente(){
        logger.info("Cliente a eliminar: " + this.clienteSeleccionado);
        this.clienteService.eliminarCliente(this.clienteSeleccionado);
        //Eliminar el registro de la lista de clientes
        this.clientes.remove(this.clienteSeleccionado);
        //reiniciar el objeto cliente seleccionado
        this.clienteSeleccionado = null;
        //confirmar accion
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente eliminado"));
        PrimeFaces.current().ajax.update("formulario-clientes:mensaje-emergente","formulario-clientes:tabla-clientes");
    }
}
