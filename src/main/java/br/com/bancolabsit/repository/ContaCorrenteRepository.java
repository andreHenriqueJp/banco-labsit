package br.com.bancolabsit.repository;

import br.com.bancolabsit.model.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long> {

    ContaCorrente findByNumeroConta(String numeroConta);
}
