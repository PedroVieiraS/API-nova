package com.api.senaibank.classe.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.senaibank.classe.Conta;
import com.api.senaibank.classe.Transacao;
import com.api.senaibank.classe.repository.ContaRepository;


@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    public Conta create(Conta conta){
        return contaRepository.save(conta);

    }
    public List<Conta> getAll(){
        return contaRepository.findAll();
    }
    public Conta getByid(Long id){
        return contaRepository.findById(id).orElse(null);

    }
    public Conta atualizarConta(Conta conta, Long id){
       Conta contaAtualizar = getByid(id);
        if (contaAtualizar == null) {
            return null;
            }
            contaAtualizar.setSaldo(conta.getSaldo());
           return contaRepository.save(contaAtualizar);
    }
    public void delete(Long id){
        contaRepository.deleteById(id);
    }
    public boolean temSaldo(Transacao transacao) {
        // Comprarar o saldo da conta origem com o valor da transacao
        Conta conta = getByid(transacao.getContaOrigem().getNumeroConta());

        boolean temSaldo = (
            conta.getSaldo() 
            >= 
            transacao.getValor()
        );
        return temSaldo;
    }
    


}
