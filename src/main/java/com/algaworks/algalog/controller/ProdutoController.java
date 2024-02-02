package com.algaworks.algalog.controller;

import com.algaworks.algalog.dtos.ProdutoDto;
import com.algaworks.algalog.model.Produto;
import com.algaworks.algalog.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin("*")
@RequestMapping("/produto")
@RestController
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public List<Produto> listarClientes(){
        return produtoService.listarProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarProduto(@PathVariable Long id) {
        Optional<Produto> produtoOptional = produtoService.buscarProduto(id);
        if (produtoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> salvarProduto(@RequestBody @Valid ProdutoDto produtoDto) {
        var produto = new Produto();
        produto.setNome(produtoDto.nome());
        produto.setQuantidade(produtoDto.quantidade());
        produto.setPreço(produtoDto.preço());
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvarProduto(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarProduto(@PathVariable(value = "id") Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarProduto(@PathVariable(value = "id") Long id, @RequestBody @Valid ProdutoDto produtoDto) throws Exception {
        Optional<Produto> produtoOptional = produtoService.buscarProduto(id);

        if (produtoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        Produto produto = produtoOptional.orElseThrow(() -> new Exception("Produto não encontrado"));
        produto.setNome(produtoDto.nome());
        produto.setQuantidade(produtoDto.quantidade());
        produto.setPreço(produtoDto.preço());
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.salvarProduto(produto));
    }
}