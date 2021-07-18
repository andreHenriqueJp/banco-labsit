package br.com.bancolabsit.service;

import br.com.bancolabsit.exceptions.CustomException;
import br.com.bancolabsit.model.*;
import br.com.bancolabsit.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ContaCorrenteService {

    @Autowired
    private ContaCorrenteRepository contaCorrenteRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    private AgenciaRepository agenciaRepository;

    public ContaCorrente salvarConta(ContaCorrente contaCorrente){

        ContaCorrente conta = contaCorrenteRepository.findByNumeroConta(contaCorrente.getNumeroConta());
        if(conta != null){
            throw new CustomException("Numero da conta já existe");
        }
        return contaCorrenteRepository.save(contaCorrente);
    }

    public ContaCorrente atualizarClienteConta(Long idConta, ContaCorrente contaCorrente){

        ContaCorrente contaAtual = contaCorrenteRepository.findById(idConta).get();

        PessoaFisica pessoaFisicaAtual = null;

        PessoaJuridica pessoaJuridicaAtual = null;

        Agencia agenciaAtual = agenciaRepository.findById(contaAtual.getAgencia().getIdAgencia()).get();

        if(Objects.nonNull(contaCorrente.getPessoaFisica())){

            pessoaFisicaAtual = pessoaFisicaRepository
                    .findById(contaAtual.getPessoaFisica().getIdPessoa()).get();

            pessoaFisicaAtual.setNome(contaCorrente.getPessoaFisica().getNome());
            pessoaFisicaAtual.setCpf(contaCorrente.getPessoaFisica().getCpf());
        }

        if(Objects.nonNull(contaCorrente.getPessoaJuridica())){

            pessoaJuridicaAtual = pessoaJuridicaRepository
                    .findById(contaAtual.getPessoaJuridica().getIdPessoa()).get();

            pessoaJuridicaAtual.setNome(contaCorrente.getPessoaJuridica().getNome());
            pessoaJuridicaAtual.setCnpj(contaCorrente.getPessoaJuridica().getCnpj());
        }

        agenciaAtual.setNumeroAgencia(contaCorrente.getAgencia().getNumeroAgencia());

        contaAtual = ContaCorrente.builder()
                .idContaCorrente(contaAtual.getIdContaCorrente())
                .saldo(contaAtual.getSaldo())
                .numeroConta(contaCorrente.getNumeroConta())
                .pessoaFisica(pessoaFisicaAtual)
                .pessoaJuridica(pessoaJuridicaAtual)
                .agencia(agenciaAtual)
                .build();

        contaCorrenteRepository.save(contaAtual);
        return contaAtual;
    }

    public void sacarContaCorrente(Transacao transacao){

        if(transacao.getContaCorrente().getNumeroConta()==null){
            throw new CustomException("Por favor informar o numero da conta");
        }

        ContaCorrente conta = contaCorrenteRepository.findByNumeroConta(transacao.getContaCorrente().getNumeroConta());

        if(conta.getSaldo()<transacao.getValor()){
            throw new CustomException("Saldo Insuficiente");
        }

        conta.setSaldo(conta.getSaldo()-transacao.getValor());
        contaCorrenteRepository.save(conta);

        transacao.setContaCorrente(conta);
        transacaoRepository.save(transacao);
    }

    public void depositarContaCorrente(Transacao transacao){

        if(transacao.getContaCorrente().getNumeroConta()==null){
            throw new CustomException("Por favor informar o numero da conta");
        }

        ContaCorrente conta = contaCorrenteRepository.findByNumeroConta(transacao.getContaCorrente().getNumeroConta());

        conta.setSaldo(conta.getSaldo()+transacao.getValor());
        contaCorrenteRepository.save(conta);

        transacao.setContaCorrente(conta);
        transacaoRepository.save(transacao);
    }

    public Double consultarSaldoConta(String numeroConta){
        ContaCorrente conta = contaCorrenteRepository.findByNumeroConta(numeroConta);
        return conta.getSaldo();
    }

    public List<Transacao> consultarExtratoConta(String numeroConta){

        ContaCorrente conta = contaCorrenteRepository.findByNumeroConta(numeroConta);

        return transacaoRepository.findByContaCorrente(conta);
    }


}
