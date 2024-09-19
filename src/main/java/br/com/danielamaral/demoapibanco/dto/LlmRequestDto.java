package br.com.danielamaral.demoapibanco.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Request")
public class LlmRequestDto {
    private String solicitacao;
    private String url;
}
