package br.com.danielamaral.demoapibanco.controller;

import br.com.danielamaral.demoapibanco.dto.ContaDto;
import br.com.danielamaral.demoapibanco.dto.TransferenciaDto;
import br.com.danielamaral.demoapibanco.repository.ContaRepository;
import br.com.danielamaral.demoapibanco.service.ContaService;
import br.com.danielamaral.demoapibanco.service.GeminiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conta")
public class ContaController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ContaService contaService;

    @Autowired
    private GeminiService geminiService;

    @GetMapping(value = "ia/{duvida}", produces = "application/json")
    public ResponseEntity consultarIA(@PathVariable String duvida){
        try{
            String msg = geminiService.consultar(duvida);
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        }catch (Exception ex){
            logger.error(ex.getMessage(),ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{conta}", produces = "application/json")
    public ResponseEntity consultarSaldo(@PathVariable String conta){
        try{
            ContaDto contaDto = contaRepository.findByConta(conta).toDto();
            return ResponseEntity.status(HttpStatus.OK).body(contaDto);
        }catch (Exception ex){
            logger.error(ex.getMessage(),ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/transferencia", produces = "application/json")
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
