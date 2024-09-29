package br.com.danielamaral.demoapibanco.controller;

import br.com.danielamaral.demoapibanco.dto.ContaDto;
import br.com.danielamaral.demoapibanco.dto.TransferenciaDto;
import br.com.danielamaral.demoapibanco.repository.ContaRepository;
import br.com.danielamaral.demoapibanco.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conta")
@Tag(name = "Conta")
public class ContaController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ContaService contaService;

    @GetMapping(value = "/{conta}", produces = "application/json")
    @Operation(summary = "Consulta o saldo da conta", description = "Retorna o saldo atualizado da conta consultada")
    public ResponseEntity consultarSaldo(@PathVariable String conta){
        try{
            ContaDto contaDto = contaRepository.findByNumero(conta).toDto();
            return ResponseEntity.status(HttpStatus.OK).body(contaDto);
        }catch (Exception ex){
            logger.error(ex.getMessage(),ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/transferencia", produces = "application/json")
    @Operation(summary = "Realiza a transferência entre contas bancarias", description = "Retorna o saldo atualizado da conta origem")
    public ResponseEntity transferir(@RequestBody TransferenciaDto transferenciaDto){

        try {
            Double saldoAtualizado = contaService.transferir(transferenciaDto);
            return ResponseEntity.status(HttpStatus.OK).body(saldoAtualizado);
        }catch (Exception ex){
            logger.error(ex.getMessage(),ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/pix/chave/{chave}/valor/{valor}", produces = "application/json")
    @Operation(summary = "Realiza o PIX de um valor informado para a conta informada", description = "Retorna se a operacao foi concluida com sucesso")
    public ResponseEntity pix(@PathVariable Long chave,@PathVariable Double valor){

        try {
            contaService.realizarPix(chave,valor);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception ex){
            logger.error(ex.getMessage(),ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
