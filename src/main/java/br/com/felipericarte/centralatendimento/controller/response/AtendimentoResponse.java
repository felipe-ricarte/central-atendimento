package br.com.felipericarte.centralatendimento.controller.response;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AtendimentoResponse {
    private Long id;
    private String texto;
    private StatusDemandaEnum statusDemanda;
    private TipoAtendimentoResponse tipoAtendimento;
    private AtendenteResponse atendente;
}
