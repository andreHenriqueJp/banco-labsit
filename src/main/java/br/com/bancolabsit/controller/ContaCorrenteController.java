package br.com.bancolabsit.controller;

import br.com.bancolabsit.model.ContaCorrente;
import br.com.bancolabsit.model.Transacao;
import br.com.bancolabsit.service.ContaCorrenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaCorrenteController {

    @Autowired
    private ContaCorrenteService contaCorrenteService;

    @PostMapping(value = "/cadastrar-cliente")
    public ResponseEntity<ContaCorrente> cadastrarCliente(@RequestBody ContaCorrente contaCorrente){

        return ResponseEntity.ok(contaCorrenteService.salvarConta(contaCorrente));
    }

    @PutMapping(value = "/atualizar-cliente/{idConta}")
    public ResponseEntity<ContaCorrente> atualizarCliente(
            @RequestBody ContaCorrente contaCorrente, @PathVariable Long idConta){

        return ResponseEntity.ok(contaCorrenteService.atualizarClienteConta(idConta, contaCorrente));
    }

    @PostMapping(value = "/sacar")
    public ResponseEntity<Void> sacar(@RequestBody Transacao transacao){

        contaCorrenteService.sacarContaCorrente(transacao);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/depositar")
    public ResponseEntity<Void> depositar(@RequestBody Transacao transacao){

        contaCorrenteService.depositarContaCorrente(transacao);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "consultar-saldo/{numeroConta}")
    public ResponseEntity<Double> consultarSaldo (@PathVariable String numeroConta){

        return ResponseEntity.ok(contaCorrenteService.consultarSaldoConta(numeroConta));
    }

    @GetMapping(value = "consultar-extrato/{numeroConta}")
    public ResponseEntity<List<Transacao>> consultarExtrato(@PathVariable String numeroConta){

        return ResponseEntity.ok(contaCorrenteService.consultarExtratoConta(numeroConta));
    }

}

