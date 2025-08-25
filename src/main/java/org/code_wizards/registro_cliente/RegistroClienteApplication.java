package org.code_wizards.registro_cliente;

import org.code_wizards.registro_cliente.entity.Cliente;
import org.code_wizards.registro_cliente.service.IClienteService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;


//@SpringBootApplication
public class RegistroClienteApplication implements CommandLineRunner {

	//inyeccion de dependencias
	@Autowired
	private IClienteService clienteService;

	//Profesionalizar nuestro SOUT  con un Logger
	private static final Logger logger = LoggerFactory.getLogger(RegistroClienteApplication.class);
	//Agregar un String para salto de linea
	String salto = System.lineSeparator();
	//antes de iniciar

	public static void main(String[] args) {

		logger.info("Iniciamos la aplicacion");
		SpringApplication.run(RegistroClienteApplication.class, args);
		//al finalizar
		logger.info("Finalizando la aplicacion");
	}

	@Override
	public void run(String... args) throws Exception {

		registroClientesApp();
	}
	private void registroClientesApp(){
		logger.info("+++++ Bienvenido a la aplicacion de Registro Clientes +++++");
		var salir = false;
		var consola = new Scanner(System.in);
		while (!salir){
			var opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola, opcion);
			logger.info(salto);
		}
	}

	private int mostrarMenu(Scanner consola){
		logger.info("""
             ***Aplicacion***
             1.Listar Clientes
             2.Buscar Clientes
             3.Agregar Cliente
             4.Modificar Cliente
             5.Eliminar Cliente
             6.Salir
             """);
		var opcion = Integer.parseInt(consola.nextLine());
		return opcion;
	}

	private boolean ejecutarOpciones(Scanner consola, int opcion){
		var salir = false;
		switch (opcion){
			case 1 -> {
				logger.info(salto + "***LISTA DE CLIENTES***" + salto);
				List<Cliente> clientes = clienteService.listarClientes();
				clientes.forEach(cliente -> logger.info(cliente.toString() + salto));
			}
			case 2 -> {
				logger.info(salto + "***BUSCAR CLIENTE POR CODIGO***" + salto);
				var codigo = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteService.buscarClienteporId(codigo);
				if(cliente != null){
					logger.info("Cliente encontrado: " + cliente + salto);
				}else{
					logger.info("Cliente No encontrado " + cliente + salto);
				}
			}
			case 3 ->{
				logger.info(salto + "***AGREGAR CLIENTE***" + salto);
				logger.info("Ingrese el nombre del cliente: ");
				var nombre = consola.nextLine();
				logger.info("Ingrese el apellido del cliente: ");
				var apellido = consola.nextLine();
				logger.info("Ingrese el telefono del cliente: ");
				var telefono = consola.nextLine();
				logger.info("Ingrese el correo del cliente: ");
				var correo = consola.nextLine();
				logger.info("Ingrese el genero del cliente: ");
				var genero = consola.nextLine();
				logger.info("Ingrese la edad del cliente: ");
				var edad = Integer.parseInt(consola.nextLine());
				var cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setTelefono(telefono);
				cliente.setCorreo(correo);
				cliente.setGenero(genero);
				cliente.setEdad(edad);
				clienteService.guardarCliente(cliente);
				logger.info("Cliente agregado: " + cliente + salto);
			}
			case 4 -> {
				logger.info(salto + "***MODIFICAR CLIENTE***" + salto);
				logger.info("Agregue el usuario a modificar");
				var codigo = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteService.buscarClienteporId(codigo);
				if (cliente != null){
					logger.info("Ingrese el nombre del cliente: ");
					var nombre = consola.nextLine();
					logger.info("Ingrese el apellido del cliente: ");
					var apellido = consola.nextLine();
					logger.info("Ingrese el telefono del cliente: ");
					var telefono = consola.nextLine();
					logger.info("Ingrese el correo del cliente: ");
					var correo = consola.nextLine();
					logger.info("Ingrese el genero del cliente: ");
					var genero = consola.nextLine();
					logger.info("Ingrese la edad del cliente: ");
					var edad = Integer.parseInt(consola.nextLine());
					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setTelefono(telefono);
					cliente.setCorreo(correo);
					cliente.setGenero(genero);
					cliente.setEdad(edad);
					clienteService.guardarCliente(cliente);
					logger.info("Cliente modificado: " + cliente + salto);
				}else {
					logger.info("Cliente NO encontrado " + cliente + salto);
				}
			}
			case 5 -> {
				logger.info(salto + "***ELIMINAR CLIENTE***" + salto);
				logger.info("Ingrese el cliente a eliminar: ");
				var codigo = Integer.parseInt(consola.nextLine());
				var cliente = clienteService.buscarClienteporId(codigo);
				if (cliente != null){
					clienteService.eliminarCliente(cliente);
					logger.info("Cliente eliminado exitosamente " + cliente + salto);
				} else {
					logger.info("Cliente No encontrado " + cliente + salto);
				}
			}
			case 6 -> {
				logger.info("Saliendo..." + salto + salto);
				salir = true;
			}
			default -> logger.info("Opcion invalida");
		}
		return salir;
	}
}