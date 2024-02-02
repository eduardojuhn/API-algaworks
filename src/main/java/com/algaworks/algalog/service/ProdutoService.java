package com.algaworks.algalog.service;

import com.algaworks.algalog.dtos.ProdutoDto;
import com.algaworks.algalog.model.Produto;
import com.algaworks.algalog.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarProduto(Long id) {
        return produtoRepository.findById(id);
    }

    @Transactional
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long produtoId) {
        produtoRepository.deleteById(produtoId);
    }

    public Produto atualizarProduto(Long id, @Valid ProdutoDto produtoDto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(produtoDto.nome());
        produto.setQuantidade(produtoDto.quantidade());
        produto.setPreço(produtoDto.preço());

        return produtoRepository.save(produto);
    }
}
