package com.api.senaibank.classe.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.senaibank.classe.Endereco;

import com.api.senaibank.classe.service.EnderecoService;


@RestController
@RequestMapping("enderecos")
public class EnderecoController {

 @Autowired
 EnderecoService enderecoService;
 
 @PostMapping
 public ResponseEntity<Endereco> create(@RequestBody Endereco endereco){
    return ResponseEntity.ok(enderecoService.create(endereco));
 }
 @GetMapping
 public ResponseEntity<List<Endereco>> getAll(){
    return ResponseEntity.ok(enderecoService.getAll());

 }
 @GetMapping("/{id}")
 public ResponseEntity<Endereco> getByid(@PathVariable Long id){
    return ResponseEntity.ok(enderecoService.getById(id));
 }
 @PutMapping("/{id}")
 public ResponseEntity<Endereco> update(@PathVariable Long id, @RequestBody Endereco endereco){
    return ResponseEntity.ok(enderecoService.update(endereco, id));
 }
 @DeleteMapping("/{id}")
 public ResponseEntity<Void> delete(@PathVariable Long id){
enderecoService.delete(id);
 
return ResponseEntity.noContent().build();
 }


}
