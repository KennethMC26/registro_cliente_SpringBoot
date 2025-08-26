package org.code_wizards.registro_cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegistroClienteWeb {
    public static void main (String [] args){
        SpringApplication.run(RegistroClienteWeb.class, args);
        System.out.println("Holi");
    }
}
