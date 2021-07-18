package br.com.bancolabsit.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa_juridica")
@Data
public class PessoaJuridica extends Pessoa{

    private String cnpj;
}
