package br.com.danielamaral.demoapibanco.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Transferencia")
public class TransferenciaDto {

    private String contaOrigem;
    private String contaDestino;
    private Double valor;
}
