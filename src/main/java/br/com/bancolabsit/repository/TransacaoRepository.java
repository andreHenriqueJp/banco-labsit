package br.com.bancolabsit.repository;

import br.com.bancolabsit.model.ContaCorrente;
import br.com.bancolabsit.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findByContaCorrente(ContaCorrente contaCorrente);
}
