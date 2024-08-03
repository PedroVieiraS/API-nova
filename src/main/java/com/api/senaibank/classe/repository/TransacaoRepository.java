package com.api.senaibank.classe.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api.senaibank.classe.Transacao;
public interface TransacaoRepository extends JpaRepository<Transacao,Long>{

}
