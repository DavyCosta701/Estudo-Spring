package com.company.davyc;

import com.company.davyc.domain.entity.Cliente;
import com.company.davyc.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
    public class StudyApplication {

    @Bean
    public CommandLineRunner runner(@Autowired Clientes clientes){
        return args ->{
            clientes.salvarCliente(new Cliente("Jorge"));
            clientes.salvarCliente(new Cliente("JorgeDois"));
            clientes.buscaCliente(3).forEach(System.out::println);
            //clientes.deletaCliente(2);
            //clientes.buscaCliente(2).forEach(System.out::println);
            List <Cliente> listCliente = clientes.listaCliente();
            listCliente.forEach(cliente -> cliente.setNome(cliente.getNome() + " UPDT"));
            listCliente.forEach(clientes::updateCliente);
            clientes.listaCliente().forEach(System.out::println);

        };
    }

    @GetMapping("/")
    public String startPage(){
        return "<h1> Start Page </h1>";
    }

        public static void main(String[] args) {
            SpringApplication.run(StudyApplication.class, args);
        }
    }
