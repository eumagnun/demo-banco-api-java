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
    @Schema(name = "contaOrigem", description = "Conta de origem")
    private String contaOrigem;
    @Schema(name = "contaDestino", description = "Conta de destino")
    private String contaDestino;
    @Schema(name ="valor", description = "Valor a ser transferido")
    private Double valor;
}
