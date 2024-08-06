package com.api.senaibank.classe.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.senaibank.classe.Conta;
import com.api.senaibank.classe.Transacao;
public interface TransacaoRepository extends JpaRepository<Transacao,Long>{

    List<Transacao> findByContaOrigemOrContaDestinoOrderByDataDesc(
        Conta contaOrigem, 
        Conta contaDestino
    );
}
