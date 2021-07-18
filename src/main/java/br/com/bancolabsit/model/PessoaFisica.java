package br.com.bancolabsit.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa_fisica")
@Data
public class PessoaFisica extends Pessoa{

    private String cpf;


}
