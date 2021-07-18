package br.com.bancolabsit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transacao")
@Data
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransacao;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date data;

    private Double valor;

    @ManyToOne
    @JoinColumn(name = "id_conta_corrente")
    private ContaCorrente contaCorrente;

}
