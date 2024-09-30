package br.com.danielamaral.demoapibanco.service;

import br.com.danielamaral.demoapibanco.dto.TransferenciaDto;
import br.com.danielamaral.demoapibanco.repository.ContaRepository;
import br.com.danielamaral.demoapibanco.repository.entity.ContaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;


    public Double transferir(TransferenciaDto transferenciaDto) throws Exception {
        ContaEntity contaOrigem = repository.findByNumero(transferenciaDto.getContaOrigem());
        ContaEntity contaDestino = repository.findByNumero(transferenciaDto.getContaDestino());

        if(contaOrigem.getSaldo() >= transferenciaDto.getValor()){
            contaOrigem.setSaldo(contaOrigem.getSaldo()-transferenciaDto.getValor());
            contaDestino.setSaldo(contaDestino.getSaldo()+transferenciaDto.getValor());
        }else{
            throw new Exception("Saldo insuficiente");
        }

        repository.save(contaOrigem);
        repository.save(contaDestino);

        return contaOrigem.getSaldo();

    }

    public void realizarPix(Long chave, Double valor){
        ContaEntity contaEntity = repository.findById(chave).get();
        contaEntity.setSaldo(contaEntity.getSaldo()+valor);
        repository.save(contaEntity);
    }

}
