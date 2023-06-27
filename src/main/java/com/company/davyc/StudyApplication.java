package com.company.davyc;

import com.company.davyc.domain.entity.Cliente;
import com.company.davyc.domain.entity.Pedido;
import com.company.davyc.domain.repository.ClientesSD;
import com.company.davyc.domain.repository.PedidoSD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
@RestController
    public class StudyApplication {

    @Bean
    public CommandLineRunner runner(@Autowired ClientesSD clientes, @Autowired PedidoSD pedidos){
        return args ->{

            Cliente david = clientes.save(new Cliente("David"));

            //clientes.findAll().forEach(System.out::println);
            //clientes.findByNOMELike("Another").forEach(System.out::println);
            //System.out.println(clientes.findOneByNOMEEquals("David"));
            Pedido pedido = new Pedido();
            pedido.setCliente(david);
            pedido.setData_Pedido(LocalDate.now());
            pedido.setTotal(BigDecimal.valueOf(100));
            pedidos.save(pedido);

            //System.out.println(clientes.findClienteFetchPedidos(1));
            pedidos.getPedidosByCliente(david).forEach(System.out::println);
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
