package com.api.senaibank.classe;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Data;

@EqualsAndHashCode
@Entity
@Data
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false, length = 11, unique = true)
    private String cpf;
    
    @Column(length = 11)
    private String telefone;

    @Column(unique = true)
    private String email;

    @Column(name = "data_nascimento")
    private LocalDate datanascimento;

    @OneToOne
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @Column(name = "cliente_ativo", nullable = false)
    private boolean ativo = true;

}
