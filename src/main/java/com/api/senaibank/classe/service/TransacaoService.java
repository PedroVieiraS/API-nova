package com.api.senaibank.classe.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.senaibank.classe.Conta;
import com.api.senaibank.classe.Transacao;
import com.api.senaibank.classe.repository.TransacaoRepository;

@Service
public class TransacaoService {

   @Autowired
   TransacaoRepository transacaoRepository;

   @Autowired
   ContaService contaService;

   public List<Transacao> getAll() {
       return transacaoRepository.findAll();
   }

   public Transacao getById(Long id) {
       return transacaoRepository.findById(id).orElse(null);
   }

   public List<Transacao> getExtrato(Long id) {
       // Tratar possível erro de retorno null
       Conta conta = contaService.getByid(id);

       return transacaoRepository.findByContaOrigemOrContaDestinoOrderByDataDesc(conta, conta);
   }

   public Transacao create(Transacao novaTransacao) {
       // Tratar a possibilidade de valores nulos nos atributos da transacao
       Transacao transacao = new Transacao();

       Conta contaOrigem = contaService.getByid(novaTransacao.getContaOrigem().getNumeroConta());
       Conta contaDestino = contaService.getByid(novaTransacao.getContaDestino().getNumeroConta());

       contaOrigem.sacar(novaTransacao.getValor());
       contaDestino.depositar(novaTransacao.getValor());

       contaService.create(contaDestino);
       contaService.create(contaOrigem);
       
       transacao.setValor(novaTransacao.getValor());
       transacao.setContaDestino(contaService.getByid(novaTransacao.getContaDestino().getNumeroConta()));
       transacao.setContaOrigem(contaService.getByid(novaTransacao.getContaOrigem().getNumeroConta()));
       transacao.setTipoTransacao(novaTransacao.getTipoTransacao());

       return transacaoRepository.save(transacao);
   }

   public Transacao update(Long id, Transacao novaTransacao) {
       Transacao transacao = transacaoRepository.findById(id).orElse(null);
       if (novaTransacao == null) {
           return null;
       }
       // Incluir os atributos que deseja atualizar
       return transacaoRepository.save(transacao);
   }

   public void delete(Long id) {
       transacaoRepository.deleteById(id);
   }

}
