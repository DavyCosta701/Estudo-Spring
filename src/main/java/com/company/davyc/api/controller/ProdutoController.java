package com.company.davyc.api.controller;

import com.company.davyc.domain.entity.Produto;
import com.company.davyc.domain.repository.ProdutoSD;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoSD produtoSD;

    public ProdutoController(ProdutoSD produtoSD) {
        this.produtoSD = produtoSD;
    }

    @GetMapping("/get/Produto/{id}")
    public Produto getProdutoByID(@PathVariable Integer id){
        return produtoSD.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NADA"));
    }

    @PostMapping("/post/Produto")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto inserirProduto(@RequestBody @Valid Produto produto){
        return produtoSD.save(produto);
    }

    @PutMapping("/put/Produto/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizaProduto(@RequestBody @Valid Produto produto, @PathVariable Integer id){
        produtoSD.findById(id).map(produto1 -> {
            produto.setID(produto1.getID());
            return produtoSD.save(produto);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NADA"));
    }

    @GetMapping("/get/Produto/query")
    public List<Produto> queryProduto(Produto produto){
        ExampleMatcher exampleMatcher = ExampleMatcher.
                matching().
                withIgnoreCase().
                withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Produto> example = Example.of(produto, exampleMatcher);
        return produtoSD.findAll(example);
    }

    @DeleteMapping("/delete/Produto/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduto(@PathVariable Integer id){
        produtoSD.findById(id).map(produto -> {
            produtoSD.delete(produto);
            return produto;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não Encontrado!"));
    }

}
