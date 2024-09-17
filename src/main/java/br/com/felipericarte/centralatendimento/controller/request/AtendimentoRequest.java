package br.com.felipericarte.centralatendimento.controller.request;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class AtendimentoRequest {

    @NotEmpty(message = "{campo.texto.obrigatorio}")
    private String texto;

    @NotNull(message = "{campo.atendimento.obrigatorio}")
    private Long idTipoAtendimento;

    public Atendimento toModel() {
        return Atendimento.builder()
                .texto(texto)
                .tipoAtendimento(
                        TipoAtendimento.builder()
                                .id(idTipoAtendimento)
                                .build()
                )
                .build();
    }
}
