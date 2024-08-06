package com.api.senaibank.classe;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "id")
@Entity
@Data
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long numeroConta;

    @Column(nullable = false)
    private Double saldo = 0.0;

    @OneToOne
    @JoinColumn(name = "clientes_id", referencedColumnName = " id", nullable = false)
    private Cliente titular;

    public void depositar(double valor) {
        saldo += valor;
    }
    public void sacar(double valor) {
        saldo -= valor;
    }
    public boolean temSaldo(double valor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'temSaldo'");
    }

}
