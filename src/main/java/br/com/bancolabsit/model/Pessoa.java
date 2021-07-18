package br.com.bancolabsit.model;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Long idPessoa;

    private String nome;
}
