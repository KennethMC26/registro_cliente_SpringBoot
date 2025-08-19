package org.code_wizards.registro_cliente;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


@SpringBootApplication
public class RegistroClienteApplication implements CommandLineRunner {

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
		return 0;
	}

	private boolean ejecutarOpciones(Scanner consola, int opcion){
		return false;
	}
}
 