package com.company.davyc.api.controller;

import com.company.davyc.domain.entity.Cliente;
import com.company.davyc.domain.repository.ClientesSD;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClientesSD clientesSD;

    public ClienteController(ClientesSD clientesSD) {
        this.clientesSD = clientesSD;
    }

    @GetMapping("/get/Cliente/{id}")
    public Cliente getClienteByID(@PathVariable("id") Integer id) {
        return clientesSD.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado!"));
    }

    @PostMapping("/post/Cliente/")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente saveCliente(@RequestBody @Valid Cliente cliente){
        return clientesSD.save(cliente);

    }

    @DeleteMapping("/delete/Cliente/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Integer id){
        clientesSD.findById(id).map(cliente -> {
            clientesSD.delete(cliente);
            return cliente;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado!"));
    }

    @PutMapping("/put/Cliente/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Cliente updateCliente(@PathVariable Integer id,
                                 @RequestBody @Valid  Cliente cliente){
        return clientesSD.findById(id).map(cliente1 -> {
            cliente.setID(cliente1.getID());
            clientesSD.save(cliente);
            return cliente1;
        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }
    @GetMapping("/get/Cliente/")
    
    public List<Cliente> listaAllCliente(){
        return clientesSD.findAll();
    }

    @GetMapping("/get/Cliente/query")
    
    public List<Cliente> listaALlCliente(Cliente filter){
        ExampleMatcher exampleMatcher = ExampleMatcher.
                matching().
                withIgnoreCase().
                 withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Cliente> example = Example.of(filter, exampleMatcher);
        return clientesSD.findAll(example);
    }
}

