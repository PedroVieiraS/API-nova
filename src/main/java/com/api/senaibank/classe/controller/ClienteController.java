package com.api.senaibank.classe.controller;

import com.api.senaibank.classe.Cliente;
import com.api.senaibank.classe.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> criarClientes(@RequestBody Cliente cliente) {
        try {
            Cliente novoCliente = clienteService.create(cliente);
            return ResponseEntity.ok(novoCliente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/ativos")
    public ResponseEntity<List<Cliente>> getAllAtivos(){
        List<Cliente> clientes = clienteService.getAllAtivos();
        return ResponseEntity.ok(clientes);
        
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.getAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.getById(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente clienteNovo) {
        Cliente clienteExistente = clienteService.getById(id);

        if (clienteExistente == null) {
            return ResponseEntity.notFound().build();
        }
        Cliente clienteSalvo = clienteService.update(id, clienteExistente, clienteNovo);

        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deletarCliente(@PathVariable Long id) {
        
        Cliente cliente = clienteService.getById(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
