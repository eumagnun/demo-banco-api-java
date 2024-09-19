package br.com.danielamaral.demoapibanco.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Conta")
public class ContaDto {

    private long id;
    private String conta;
    private Double saldo;
}
