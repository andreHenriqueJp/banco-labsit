package br.com.bancolabsit.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "conta_corrente")
@Data
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta_corrente")
    private Long idContaCorrente;

    @Column(nullable = false)
    private String numeroConta;

    private Double saldo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pessoa_fisica")
    private PessoaFisica pessoaFisica;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pessoa_juridica")
    private PessoaJuridica pessoaJuridica;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_agencia", nullable = false)
    private Agencia agencia;

    public ContaCorrente() {
    }

    @Builder
    public ContaCorrente(Long idContaCorrente, String numeroConta, Double saldo, PessoaFisica pessoaFisica, PessoaJuridica pessoaJuridica, Agencia agencia) {
        this.idContaCorrente = idContaCorrente;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.pessoaFisica = pessoaFisica;
        this.pessoaJuridica = pessoaJuridica;
        this.agencia = agencia;
    }
}
