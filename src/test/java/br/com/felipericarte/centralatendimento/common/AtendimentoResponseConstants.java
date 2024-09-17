package br.com.felipericarte.centralatendimento.common;

import br.com.felipericarte.centralatendimento.controller.response.AtendimentoResponse;
import br.com.felipericarte.centralatendimento.controller.response.TipoAtendimentoResponse;

public class AtendimentoResponseConstants {
	
	public static final AtendimentoResponse Atendimento_RESPONSE_VALIDA_MOCK =
				AtendimentoResponse.builder()
					.texto("Problemas durante a consulta de saldo do cartão")
					.tipoAtendimento(
						TipoAtendimentoResponse.builder()
						.id(1L)
						.build()
					)
				.build();
	
}