package br.com.danielamaral.demoapibanco.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Conta", description = "Conta Bancária")
public class ContaDto {

    private long id;
    @Schema(name = "numero",description = "Número da conta")
    private String numero;
    @Schema(name = "saldo",description = "Saldo da conta")
    private Double saldo;
}
