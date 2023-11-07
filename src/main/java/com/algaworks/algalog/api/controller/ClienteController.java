package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> listar(){
        Cliente cliente1 = new Cliente(1L, "Gabriel", "gabriel@feitosa.com", "123123");
        Cliente cliente2 = new Cliente(2L, "Emily", "emily@pereira.com", "123124");

        return Arrays.asList(cliente1, cliente2);
    }
}
