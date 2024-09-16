package br.com.felipericarte.centralatendimento.controller.response;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TipoAtendimentoResponse {

    private Long id;
    private String nome;
}
